package com.food.recipe.api.model.request.like;

import com.food.recipe.api.model.enums.LikeEnums;
import com.food.recipe.api.model.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LikedBaseRequest extends BaseRequest {

  private Long postId;

  private Long commentId;

  private Long replyCommentId;

  private Long recipeId;

  private LikeEnums likeEnums;
}
