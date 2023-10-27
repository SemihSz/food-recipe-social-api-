package com.food.recipe.api.service.executable.comment;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.comment.CommentsEntity;
import com.food.recipe.api.model.input.comment.AddCommentInput;
import com.food.recipe.api.model.response.comment.CommentResponse;
import com.food.recipe.api.repository.post.comment.CommentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by Semih, 15.10.2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AddCommentService implements SimpleTask<AddCommentInput, CommentResponse> {

    private final CommentsRepository commentsRepository;

    @Override
    public CommentResponse apply(AddCommentInput addCommentInput) {

        final CommentsEntity comment = CommentsEntity.builder()
                .body(addCommentInput.getDescription())
                .user(addCommentInput.getUser())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .post(addCommentInput.getPost())
                .build();

        commentsRepository.save(comment);

        return CommentResponse.builder().commentId(comment.getId()).build();
    }
}
