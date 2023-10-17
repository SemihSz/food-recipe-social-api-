package com.food.recipe.api.model.request.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCommentRequest {

    private Long userId;

    private String username;

    private Long postId;
}
