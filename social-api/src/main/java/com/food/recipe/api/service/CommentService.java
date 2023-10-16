package com.food.recipe.api.service;

import com.food.recipe.api.model.request.comment.CommentDeleteRequest;
import com.food.recipe.api.model.request.comment.CommentUpdateRequest;
import com.food.recipe.api.model.request.comment.CreateCommentRequest;
import com.food.recipe.api.model.response.comment.CommentResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 15.10.2023
 */
@Service
public interface CommentService {

    /**
     *
     * @param createCommentRequest
     * @return
     */
    CommentResponse addComment(CreateCommentRequest createCommentRequest);

    CommentResponse updateComment(CommentUpdateRequest commentUpdateRequest);

    CommentResponse deleteComment(CommentDeleteRequest commentDeleteRequest);

}
