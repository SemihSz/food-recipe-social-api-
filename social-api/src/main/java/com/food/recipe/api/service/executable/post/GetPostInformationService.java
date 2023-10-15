package com.food.recipe.api.service.executable.post;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 15.10.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GetPostInformationService implements SimpleTask<Long, PostEntity> {

    private final PostRepository postRepository;

    /**
     * This executable service provide post information from DB.
     * @param postId the function argument
     * @return
     */
    @Override
    public PostEntity apply(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
}
