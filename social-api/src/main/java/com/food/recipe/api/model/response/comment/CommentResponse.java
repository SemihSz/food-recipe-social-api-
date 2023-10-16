package com.food.recipe.api.model.response.comment;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Semih, 15.10.2023
 */
@Data
@Builder
public class CommentResponse {

    private Long commentId;

    private boolean isDeleted;

    private boolean isUpdated;

}
