package com.food.recipe.api.service.executable.comment;

import com.food.recipe.api.entity.post.comment.CommentEntity;
import com.food.recipe.api.repository.post.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * Created by Semih, 15.10.2023
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DeleteCommentService  implements Predicate<CommentEntity> {

    private final CommentRepository commentRepository;

    @Override
    public boolean test(CommentEntity entity) {

        commentRepository.delete(entity);

        return true;
    }
}
