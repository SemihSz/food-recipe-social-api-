package com.food.recipe.api.model.request.comment.reply;

import com.food.recipe.api.model.request.comment.CreateCommentRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseReplyCommentRequest extends CreateCommentRequest {

  private Long commentId;

}
