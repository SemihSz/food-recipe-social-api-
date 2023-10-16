package com.food.recipe.api.service.executable.comment;


import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.comment.CommentEntity;
import com.food.recipe.api.repository.post.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class GetCommentInformationService implements SimpleTask<Long, CommentEntity> {

    private final CommentRepository commentRepository;

    @Override
    public CommentEntity apply(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }
}
