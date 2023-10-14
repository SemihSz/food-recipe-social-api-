package com.food.recipe.api.service.impl;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.SavedPostEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.document.response.SaveDocumentResponse;
import com.food.recipe.api.model.input.SaveFileInput;
import com.food.recipe.api.model.request.post.CommentRequest;
import com.food.recipe.api.model.request.post.PostRequest;
import com.food.recipe.api.model.response.CreatePostResponse;
import com.food.recipe.api.repository.post.PostRepository;
import com.food.recipe.api.repository.post.SavedPostRepository;
import com.food.recipe.api.repository.user.SocialUserRepository;
import com.food.recipe.api.service.PostService;
import com.food.recipe.api.service.executable.post.SaveFileService;
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

    private final PostRepository postRepository;

    private final SavedPostRepository savedPostRepository;

    private final SaveFileService saveFileService;

    private final SocialUserRepository socialUserRepository;

    @Override
    public CreatePostResponse createPost(PostRequest request) {

        SocialUserEntity socialUserEntity = socialUserRepository.getUser(request.getUsername(), request.getId());
        if (Objects.nonNull(socialUserEntity)) {
            final SaveFileInput.SaveFileInputBuilder builder =  SaveFileInput.builder();
            builder.userId(request.getId())
                    .username(request.getUsername());
            if (Objects.nonNull(request.getBase64ImageList()) && CollectionUtils.isEmpty(request.getBase64ImageList())) {
                builder.base64String(request.getBase64StringImage());
            }
            else if (Objects.isNull(request.getBase64StringImage()) && !CollectionUtils.isEmpty(request.getBase64ImageList())){
                builder.base64StringList(request.getBase64ImageList());
            }

            List<SaveDocumentResponse> response = saveFileService.apply(builder.build());
            List<Long> documentIdList = new ArrayList<>();
            for (SaveDocumentResponse documentResponse : response) {
                documentIdList.add(documentResponse.getDocumentId());
            }
            // TODO: Retrieve data from request parameters
            final PostEntity postEntity = PostEntity.builder()
                    .description("Deneme!")
                    .imageId(documentIdList)
                    .build();
            final SavedPostEntity savedPostEntity = SavedPostEntity.builder()
                    .post(postEntity)
                    .user(socialUserEntity)
                    .build();

            if (Objects.nonNull(savedPostEntity)) {
                savedPostRepository.save(savedPostEntity);
                return CreatePostResponse.builder()
                        .createdDate(LocalDateTime.now().toString())
                        .postId(savedPostEntity.getId())
                        .build();
            }
        }
        return null;
    }

@Override
public CreatePostResponse createPostViaFile(MultipartFile[] files, String username, Long id) {
    SocialUserEntity socialUserEntity = socialUserRepository.getUser(username, id);
    if (Objects.nonNull(socialUserEntity)) {
        SaveFileInput saveFileInput = SaveFileInput.builder()
                .userId(id)
                .username(username)
                .files(files)
                .build();
        List<SaveDocumentResponse> response = saveFileService.apply(saveFileInput);
        List<Long> documentIdList = new ArrayList<>();
        for (SaveDocumentResponse documentResponse : response) {
            documentIdList.add(documentResponse.getDocumentId());
        }
        // TODO: Retrieve data from request parameters
        final PostEntity postEntity = PostEntity.builder()
                .description("Deneme!")
                .imageId(documentIdList)
                .build();
        final SavedPostEntity savedPostEntity = SavedPostEntity.builder()
                .post(postEntity)
                .user(socialUserEntity)
                .build();

        if (Objects.nonNull(savedPostEntity)) {
            savedPostRepository.save(savedPostEntity);
            return CreatePostResponse.builder()
                    .createdDate(LocalDateTime.now().toString())
                    .postId(savedPostEntity.getId())
                    .build();
        }
    }
    return null;
}

    @Override
    public Boolean addComment(CommentRequest request) {
        return null;
    }
}
