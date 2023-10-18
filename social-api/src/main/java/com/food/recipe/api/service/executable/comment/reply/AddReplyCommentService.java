package com.food.recipe.api.service.executable.comment.reply;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.comment.ReplyCommentEntity;
import com.food.recipe.api.model.input.comment.AddReplyCommentInput;
import com.food.recipe.api.model.response.comment.reply.ReplyCommentResponse;
import com.food.recipe.api.repository.post.comment.ReplyCommentEntityRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddReplyCommentService implements SimpleTask<AddReplyCommentInput, ReplyCommentResponse> {

  private final ReplyCommentEntityRepository replyCommentRepository;

  @Override
  public ReplyCommentResponse apply(AddReplyCommentInput input) {

    final ReplyCommentEntity replyComment = ReplyCommentEntity.builder()
        .comment(input.getComment())
        .body(input.getReplyCommentBody())
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .user(input.getSocialUserInfo())
        .post(input.getPost())
        .build();

    replyCommentRepository.save(replyComment);

    return ReplyCommentResponse.builder().commentId(replyComment.getId()).build();
  }
}
