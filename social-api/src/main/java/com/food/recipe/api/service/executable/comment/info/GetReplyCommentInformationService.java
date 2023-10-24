package com.food.recipe.api.service.executable.comment.info;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.comment.ReplyCommentEntity;
import com.food.recipe.api.repository.post.comment.ReplyCommentEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetReplyCommentInformationService implements SimpleTask<Long, ReplyCommentEntity> {

  private final ReplyCommentEntityRepository replyCommentEntityRepository;
  @Override
  public ReplyCommentEntity apply(Long replyCommentId) {
    return replyCommentEntityRepository.findById(replyCommentId).orElse(null);
  }
}
