package com.food.recipe.api.model.request.comment.reply;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReplyCommentRequest extends BaseReplyCommentRequest {

  private String editDescription;

}
