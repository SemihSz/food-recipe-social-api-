package com.food.recipe.api.model.comment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReplyCommentList {

    private Long userId;

    private Long postId;

    private String username;

    private String name;

    private String surname;

    private String commentDescription;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
