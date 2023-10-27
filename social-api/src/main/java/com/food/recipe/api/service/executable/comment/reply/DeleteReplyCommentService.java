package com.food.recipe.api.service.executable.comment.reply;

import com.food.recipe.api.entity.post.comment.ReplyCommentEntity;
import com.food.recipe.api.repository.post.comment.ReplyCommentEntityRepository;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteReplyCommentService implements Predicate<ReplyCommentEntity> {

  private final ReplyCommentEntityRepository replyCommentRepository;

  @Override
  public boolean test(ReplyCommentEntity replyCommentEntity) {

    replyCommentEntity.setComment(null);
    replyCommentEntity.setPost(null);
    replyCommentEntity.setUser(null);
    replyCommentRepository.delete(replyCommentEntity);

    return true;
  }
}
