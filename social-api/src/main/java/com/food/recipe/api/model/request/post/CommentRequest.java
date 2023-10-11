package com.food.recipe.api.model.request.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private Long postId;

    private String userId;

    private String username;

    private String description;
}
