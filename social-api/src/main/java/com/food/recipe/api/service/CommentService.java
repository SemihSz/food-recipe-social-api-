package com.food.recipe.api.service;

import com.food.recipe.api.model.request.comment.CommentDeleteRequest;
import com.food.recipe.api.model.request.comment.CommentUpdateRequest;
import com.food.recipe.api.model.request.comment.CreateCommentRequest;
import com.food.recipe.api.model.response.comment.CommentResponse;
import com.food.recipe.api.model.response.comment.SelectedPostCommentsResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 15.10.2023
 */
@Service
public interface CommentService {

    /**
     *
     * @param createCommentRequest
     * @return CommentResponse
     */
    CommentResponse addComment(CreateCommentRequest createCommentRequest);

    /**
     * @param commentUpdateRequest
     * @return CommentResponse
     */
    CommentResponse updateComment(CommentUpdateRequest commentUpdateRequest);

    /**
     *
     * @param commentDeleteRequest
     * @return CommentResponse
     */
    CommentResponse deleteComment(CommentDeleteRequest commentDeleteRequest);

    /**
     *
     * @param postId
     * @return SelectedPostCommentsResponse
     */
    SelectedPostCommentsResponse selectedPostComments(Long postId);

}
