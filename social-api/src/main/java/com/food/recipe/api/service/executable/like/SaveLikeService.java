package com.food.recipe.api.service.executable.like;

import com.food.recipe.api.entity.post.LikeEntity;
import com.food.recipe.api.entity.post.comment.like.CommentLikedEntity;
import com.food.recipe.api.model.enums.LikeEnums;
import com.food.recipe.api.model.input.like.LikeDislikeInput;
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
public class SaveLikeService implements Consumer<LikeDislikeInput> {

  private final CommentLikedEntityRepository commentLikedEntityRepository;

  private final LikeRepository likeRepository;
  private ReplyCommentEntityRepository replyCommentEntityRepository;

  @Override
  public void accept(LikeDislikeInput input) {

    switch (input.getLikeTypes()) {
      case RECIPE:
        final LikeEntity recipe = LikeEntity.builder()
            .post(input.getPost())
            .LikeTypes(input.getLikeTypes())
            .user(input.getUser())
            .recipe(input.getRecipe())
            .build();
        savePost(recipe);
        break;
      case POST:
        final LikeEntity post = LikeEntity.builder()
            .post(input.getPost())
            .LikeTypes(input.getLikeTypes())
            .user(input.getUser())
            .build();
        savePost(post);
        break;
      case COMMENT:
        final CommentLikedEntity comment = CommentLikedEntity.builder()
            .LikeTypes(LikeEnums.COMMENT)
            .comment(input.getComment())
            .user(input.getUser())
            .post(input.getPost())
            .build();
        saveComment(comment);
        break;
      default:
    }

  }
  private void savePost(LikeEntity entity) {
    likeRepository.save(entity);
  }
  private void saveComment(CommentLikedEntity entity) {
    commentLikedEntityRepository.save(entity);
  }
}
