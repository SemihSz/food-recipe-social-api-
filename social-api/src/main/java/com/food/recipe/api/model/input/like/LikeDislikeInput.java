package com.food.recipe.api.model.input.like;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.comment.CommentsEntity;
import com.food.recipe.api.entity.recipe.RecipeEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.enums.LikeEnums;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeDislikeInput {

  private Long commentId;

  private LikeEnums LikeTypes;

  private PostEntity post;

  private SocialUserEntity user;

  private CommentsEntity comment;

  private RecipeEntity recipe;
}
