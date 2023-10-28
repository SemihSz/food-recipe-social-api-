package com.food.recipe.api.service.executable.post;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.SavedPostEntity;
import com.food.recipe.api.model.input.post.UserPostInput;
import com.food.recipe.api.model.response.post.PostResponse;
import com.food.recipe.api.repository.post.SavedPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetSelectedUserPostsService implements SimpleTask<UserPostInput, List<PostResponse>> {

    private final SavedPostRepository savedPostRepository;

    @Override
    public List<PostResponse> apply(UserPostInput userPostInput) {

        final List<SavedPostEntity> postsEntities = savedPostRepository.findByUser(userPostInput.getUser());

        for (SavedPostEntity savedPost : postsEntities) {
            final PostEntity postEntity = savedPost.getPost();
        }

        return null;
    }
}
