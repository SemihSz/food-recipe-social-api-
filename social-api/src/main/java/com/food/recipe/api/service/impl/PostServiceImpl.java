package com.food.recipe.api.service.impl;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.SavedPostEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.document.Base64Files;
import com.food.recipe.api.model.document.response.SaveDocumentResponse;
import com.food.recipe.api.model.enums.LikeEnums;
import com.food.recipe.api.model.input.SaveFileInput;
import com.food.recipe.api.model.input.like.LikeDislikeInput;
import com.food.recipe.api.model.input.post.UserPostInput;
import com.food.recipe.api.model.request.BaseRequest;
import com.food.recipe.api.model.request.like.LikedBaseRequest;
import com.food.recipe.api.model.request.post.GetUserPostRequest;
import com.food.recipe.api.model.request.post.PostRequest;
import com.food.recipe.api.model.response.post.CreatePostResponse;
import com.food.recipe.api.model.response.post.PostResponse;
import com.food.recipe.api.model.response.post.UserPostResponse;
import com.food.recipe.api.repository.post.SavedPostRepository;
import com.food.recipe.api.service.PostService;
import com.food.recipe.api.service.executable.converter.ConvertBase64Service;
import com.food.recipe.api.service.executable.like.SaveLikeService;
import com.food.recipe.api.service.executable.post.GetPostInformationService;
import com.food.recipe.api.service.executable.post.GetSelectedUserPostsService;
import com.food.recipe.api.service.executable.post.SaveFileService;
import com.food.recipe.api.service.executable.user.GetSocialAppUserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author semih.sozdinler
 * Created by Semih, 1.10.2023
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final SavedPostRepository savedPostRepository;

    private final SaveFileService saveFileService;

    private final ConvertBase64Service convertBase64Service;

    private final GetSocialAppUserInfoService getSocialAppUserInfoService;

    private final GetPostInformationService getPostInformationService;

    private final SaveLikeService saveLikeService;

    private final GetSelectedUserPostsService getSelectedUserPostsService;

    /**
     * Create a new post based on the provided {@link PostRequest}.
     *
     * @param request The {@link PostRequest} containing post information.
     * @return A {@link CreatePostResponse} representing the result of the post creation,
     *         or null if the user or saved post entity is null.
     */
    @Override
    public CreatePostResponse createPost(PostRequest request) {

        // Get user information from a social app using a service
        final SocialUserEntity socialUserEntity = getSocialAppUserInfoService.apply(request.getId(), request.getUsername());

        if (Objects.nonNull(socialUserEntity)) {

            // Create a builder for saving a file
            final SaveFileInput.SaveFileInputBuilder builder = SaveFileInput.builder();
            builder.userId(request.getId())
                    .username(request.getUsername());

            if (Objects.nonNull(request.getBase64ImageList()) && CollectionUtils.isEmpty(request.getBase64ImageList())) {
                // If a single base64 image is provided, set it
                builder.base64String(request.getBase64StringImage());
            } else if (Objects.isNull(request.getBase64StringImage()) && !CollectionUtils.isEmpty(request.getBase64ImageList())) {
                // If a list of base64 images is provided, set them
                builder.base64StringList(request.getBase64ImageList());
            }

            // Call a service to save the file(s) and get a response
            final List<SaveDocumentResponse> response = saveFileService.apply(builder.build());

            List<Long> documentIdList = new ArrayList<>();
            for (SaveDocumentResponse documentResponse : response) {
                documentIdList.add(documentResponse.getDocumentId());
            }

            // Create a PostEntity with description and associated image IDs
            final PostEntity postEntity = PostEntity.builder()
                    .description(request.getDescription())
                    .imageId(documentIdList)
                    .build();

            // Create a SavedPostEntity containing the post and the user
            final SavedPostEntity savedPostEntity = SavedPostEntity.builder()
                    .post(postEntity)
                    .user(socialUserEntity)
                    .build();

            if (Objects.nonNull(savedPostEntity)) {
                // Save the post entity in the repository
                savedPostRepository.save(savedPostEntity);
                // Return a response with the post creation information
                return CreatePostResponse.builder()
                        .createdDate(LocalDateTime.now().toString())
                        .postId(savedPostEntity.getId())
                        .build();
            }
        }

        // Return null if the user entity is null or the saved post entity is null
        return null;
    }


    /**
     * Create a new post based on the provided {@link MultipartFile} files, username, and user ID.
     *
     * @param files    An array of {@link MultipartFile} representing the images to be posted.
     * @param username The username of the user creating the post.
     * @param id       The user ID of the user creating the post.
     * @return A {@link CreatePostResponse} representing the result of the post creation,
     *         or null if the user or saved post entity is null.
     */
    @Override
    public CreatePostResponse createPostViaFile(MultipartFile[] files, String username, Long id) {

        // Get user information from a social app using a service
        final SocialUserEntity socialUserEntity = getSocialAppUserInfoService.apply(id, username);
        if (Objects.nonNull(socialUserEntity)) {

            // Create a SaveFileInput to save the uploaded files
            final SaveFileInput saveFileInput = SaveFileInput.builder()
                    .userId(id)
                    .username(username)
                    .files(files)
                    .build();

            // Call a service to save the uploaded files and get a response
            final List<SaveDocumentResponse> response = saveFileService.apply(saveFileInput);

            List<Long> documentIdList = new ArrayList<>();
            for (SaveDocumentResponse documentResponse : response) {
                documentIdList.add(documentResponse.getDocumentId());
            }

            // TODO: Replace the hardcoded description with data from request parameters
            final PostEntity postEntity = PostEntity.builder()
                    .description("Deneme!")
                    .imageId(documentIdList)
                    .build();

            // Create a SavedPostEntity containing the post and the user
            final SavedPostEntity savedPostEntity = SavedPostEntity.builder()
                    .post(postEntity)
                    .user(socialUserEntity)
                    .build();

            if (Objects.nonNull(savedPostEntity)) {
                // Save the post entity in the repository
                savedPostRepository.save(savedPostEntity);
                // Return a response with the post creation information
                return CreatePostResponse.builder()
                        .createdDate(LocalDateTime.now().toString())
                        .postId(savedPostEntity.getId())
                        .build();
            }
        }

        // Return null if the user entity is null or the saved post entity is null
        return null;
    }


    /**
     * Convert an array of {@link MultipartFile} files to a list of {@link Base64Files}.
     *
     * @param files An array of {@link MultipartFile} representing the files to be converted.
     * @return A {@link List} of {@link Base64Files} representing the converted files.
     */
    @Override
    public List<Base64Files> convertMultipartBase64(MultipartFile[] files) {
        // Call a service to convert the Multipart files to Base64 format
        return convertBase64Service.apply(files);
    }

    /**
     * Process a "like" action for a post based on the provided request.
     *
     * @param request The request containing user and post information.
     */
    @Override
    public void likes(LikedBaseRequest request) {
        // Retrieve user and post information from the request.
        final SocialUserEntity socialUserEntity = getSocialAppUserInfoService.apply(request.getId(), request.getUsername());
        final PostEntity getPostInformation = getPostInformationService.apply(request.getPostId());

        // Check if both user and post information are not null.
        if (Objects.nonNull(socialUserEntity) && Objects.nonNull(getPostInformation)) {
            // Create a "like" input and perform the like action for the post.
            final LikeDislikeInput input = LikeDislikeInput.builder()
                .LikeTypes(LikeEnums.POST)
                .user(socialUserEntity)
                .post(getPostInformation)
                .build();

            saveLikeService.accept(input);
        }
    }

    @Override
    public void dislikes(LikedBaseRequest request) {

    }

    @Override
    public UserPostResponse userPosts(GetUserPostRequest request) {

        final SocialUserEntity socialUserEntity = getSocialAppUserInfoService.apply(request.getId(), request.getUsername());

        if (Objects.nonNull(socialUserEntity)) {

            final UserPostInput input = UserPostInput.builder()
                    .user(socialUserEntity)
                    .build();

            List<PostResponse> responseList = getSelectedUserPostsService.apply(input);

            if (!CollectionUtils.isEmpty(responseList)) {
                return UserPostResponse.builder().posts(responseList).build();
            }
        }

        return null;
    }
}
