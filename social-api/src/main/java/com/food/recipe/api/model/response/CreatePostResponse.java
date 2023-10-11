package com.food.recipe.api.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreatePostResponse {

    private Long postId;

    private LocalDateTime createdDate;
}
