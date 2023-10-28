package com.food.recipe.api.model.response.post;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class CreatePostResponse implements Serializable {

    private Long postId;

    private String createdDate;
}
