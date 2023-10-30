package com.food.recipe.api.service.executable.comment.info;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.repository.post.comment.CommentsRepository;
import com.food.recipe.api.service.executable.post.GetPostInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetSelectedPostCommentCountService implements SimpleTask<Long, Long> {

    private final CommentsRepository commentsRepository;

    private final GetPostInformationService getPostInformationService;
    @Override
    public Long apply(Long id) {

        final Long postId = id;
        // Retrieve the post information based on the post ID from the request
        final PostEntity getPostInformation = getPostInformationService.apply(postId);

        final Long count = commentsRepository.countByPost(getPostInformation);

        return null;
    }
}
