package com.food.recipe.api.service.executable.comment.info;


import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.comment.CommentsEntity;
import com.food.recipe.api.repository.post.comment.CommentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class GetCommentInformationService implements SimpleTask<Long, CommentsEntity> {

    private final CommentsRepository commentsRepository;

    @Override
    public CommentsEntity apply(Long commentId) {
        return commentsRepository.findById(commentId).orElse(null);
    }
}
