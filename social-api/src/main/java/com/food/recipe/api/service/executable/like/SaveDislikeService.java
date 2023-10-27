package com.food.recipe.api.service.executable.like;

import com.food.recipe.api.entity.post.DislikeEntity;
import com.food.recipe.api.entity.post.comment.like.CommentDislikeEntity;
import com.food.recipe.api.model.enums.LikeEnums;
import com.food.recipe.api.model.input.like.LikeDislikeInput;
import com.food.recipe.api.repository.post.DislikeRepository;
import com.food.recipe.api.repository.post.comment.like.CommentDislikeRepository;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SaveDislikeService implements Consumer<LikeDislikeInput> {

  private final DislikeRepository dislikeRepository;

  private final CommentDislikeRepository commentDislikeRepository;

  @Override
  public void accept(LikeDislikeInput input) {

    switch (input.getLikeTypes()) {
      case RECIPE:
        final DislikeEntity recipe = DislikeEntity.builder().user(input.getUser()).post(input.getPost())
            .disLikeTypes(LikeEnums.RECIPE)
            .recipe(input.getRecipe()).build();
        saveOperationDislikePost(recipe);
        break;
      case POST:
        final DislikeEntity post = DislikeEntity.builder().user(input.getUser()).post(input.getPost())
            .disLikeTypes(LikeEnums.POST)
            .build();
        saveOperationDislikePost(post);
        break;
      case COMMENT:
        final CommentDislikeEntity comment = CommentDislikeEntity.builder()
            .user(input.getUser())
            .post(input.getPost())
            .dislikeTypes(LikeEnums.COMMENT)
            .comment(input.getComment())
            .build();
        saveOperationDislikeComment(comment);
        break;
      default:
    }
  }

  private void saveOperationDislikePost(DislikeEntity entity) {
    dislikeRepository.save(entity);
  }
  private void saveOperationDislikeComment(CommentDislikeEntity entity) {
    commentDislikeRepository.save(entity);
  }
}
