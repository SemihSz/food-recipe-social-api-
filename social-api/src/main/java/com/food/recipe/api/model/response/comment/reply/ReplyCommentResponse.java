package com.food.recipe.api.model.response.comment.reply;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyCommentResponse {

    private Long commentId;

    private boolean isDeleted;

    private boolean isUpdated;

}
