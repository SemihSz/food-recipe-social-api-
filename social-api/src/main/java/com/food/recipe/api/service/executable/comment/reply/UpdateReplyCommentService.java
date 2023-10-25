package com.food.recipe.api.service.executable.comment.reply;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.comment.ReplyCommentEntity;
import com.food.recipe.api.model.request.comment.reply.UpdateReplyCommentRequest;
import com.food.recipe.api.model.response.comment.reply.ReplyCommentResponse;
import com.food.recipe.api.repository.post.comment.ReplyCommentEntityRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateReplyCommentService implements SimpleTask<UpdateReplyCommentRequest, ReplyCommentResponse> {

  private final ReplyCommentEntityRepository replyCommentEntityRepository;

  @Override
  public ReplyCommentResponse apply(UpdateReplyCommentRequest updateReplyCommentRequest) {
    final Optional<ReplyCommentEntity> replyComment = replyCommentEntityRepository.findById(updateReplyCommentRequest.getReplyCommentId());

    if (replyComment.isPresent()) {

      final ReplyCommentEntity updatedReply = replyComment.get();
      updatedReply.setUpdatedAt(LocalDateTime.now());
      updatedReply.setBody(updateReplyCommentRequest.getEditDescription());
      replyCommentEntityRepository.save(updatedReply);
      return ReplyCommentResponse.builder().commentId(updatedReply.getId()).isUpdated(Boolean.TRUE).build();
    }

    return ReplyCommentResponse.builder().isUpdated(Boolean.FALSE).build();
  }

}
