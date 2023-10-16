package com.food.recipe.api.model.response.comment;

import com.food.recipe.api.model.comment.CommentList;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SelectedPostCommentsResponse {

    private Long postId;

    private List<CommentList> comments;
}
