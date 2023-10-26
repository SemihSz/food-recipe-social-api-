package com.food.recipe.api.service.executable.like;

import com.food.recipe.api.model.input.like.LikeInput;
import com.food.recipe.api.repository.post.LikeRepository;
import com.food.recipe.api.repository.post.comment.ReplyCommentEntityRepository;
import com.food.recipe.api.repository.post.comment.like.CommentLikedEntityRepository;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService implements Consumer<LikeInput> {

  private final CommentLikedEntityRepository commentLikedEntityRepository;

  private final LikeRepository likeRepository;
  private ReplyCommentEntityRepository replyCommentEntityRepository;

  @Override
  public void accept(LikeInput input) {

    switch (input.getLikeTypes()) {
      case RECIPE:
        break;
      case POST:
        break;
      case COMMENT_LIKES:
        break;
      default:
    }

  }
}
