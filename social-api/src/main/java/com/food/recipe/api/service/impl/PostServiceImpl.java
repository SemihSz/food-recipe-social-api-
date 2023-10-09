package com.food.recipe.api.service.impl;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.model.request.post.PostRequest;
import com.food.recipe.api.repository.post.PostRepository;
import com.food.recipe.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * Created by Semih, 1.10.2023
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

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
}
