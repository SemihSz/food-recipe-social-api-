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
import com.food.recipe.api.repository.user.SocialUserRepository;
import com.food.recipe.api.service.PostService;
import com.food.recipe.api.service.executable.post.SaveFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Semih, 1.10.2023
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final SaveFileService saveFileService;

    private final SocialUserRepository socialUserRepository;

    @Override
    public Boolean createPost(PostRequest request) {

        // TODO Create new model to access db.
        PostEntity post = new PostEntity();
        post.setDescription(request.getDescription());

        final PostEntity savePost = postRepository.save(post);

        if (Objects.nonNull(savePost.getId())) {
            return Boolean.TRUE;
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
        PostEntity postEntity = PostEntity.builder()
                .description("Deneme!")
                .imageId(documentIdList)
                .build();
        SavedPostEntity savedPostEntity = SavedPostEntity.builder()
                .post(postEntity)
                .user(socialUserEntity)
                .build();
        if (Objects.nonNull(savedPostEntity)) {
            return CreatePostResponse.builder()
                    .createdDate(LocalDateTime.now())
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
