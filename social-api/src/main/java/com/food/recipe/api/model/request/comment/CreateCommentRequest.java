package com.food.recipe.api.model.request.comment;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 15.10.2023
 */

@Getter
@Setter
public class CreateCommentRequest {

    private Long userId;

    private String username;

    private Long postId;

    @Size(max = 100)
    private String description;
}
