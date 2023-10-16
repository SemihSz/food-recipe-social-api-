package com.food.recipe.api.service.executable.comment;

import com.food.recipe.api.entity.post.comment.CommentEntity;
import com.food.recipe.api.model.request.comment.CommentUpdateRequest;
import com.food.recipe.api.repository.post.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.BiPredicate;

/**
 * Created by Semih, 15.10.2023
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UpdateCommentService implements BiPredicate<CommentUpdateRequest, CommentEntity> {

    private final CommentRepository commentRepository;


    /**
     * Updated comment information
     * @param commentUpdateRequest the first input argument
     * @param entity the second input argument
     * @return boolean
     */
    @Override
    public boolean test(CommentUpdateRequest commentUpdateRequest, CommentEntity entity) {

        entity.setBody(commentUpdateRequest.getDescription());
        entity.setUpdateAt(LocalDateTime.now());
        commentRepository.save(entity);

        return true;
    }
}
