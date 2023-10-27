package com.food.recipe.api.service.executable.comment;

import com.food.recipe.api.entity.post.comment.CommentsEntity;
import com.food.recipe.api.repository.post.comment.CommentsRepository;
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
public class DeleteCommentService  implements Predicate<CommentsEntity> {

    private final CommentsRepository commentsRepository;

    @Override
    public boolean test(CommentsEntity entity) {

        entity.setPost(null);
        entity.setUser(null);
        commentsRepository.delete(entity);

        return true;
    }
}
