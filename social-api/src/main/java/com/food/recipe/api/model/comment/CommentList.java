package com.food.recipe.api.model.comment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CommentList {

    private Long userId;

    private Long postId;

    private String username;

    private String name;

    private String surname;

    private String commentDescription;

    private List<ReplyCommentList> replyComments;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
