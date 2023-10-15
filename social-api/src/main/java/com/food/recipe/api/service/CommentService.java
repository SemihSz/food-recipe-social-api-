package com.food.recipe.api.service;

import com.food.recipe.api.model.request.comment.CommentRequest;
import com.food.recipe.api.model.response.comment.CommentResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 15.10.2023
 */
@Service
public interface CommentService {

    /**
     *
     * @param commentRequest
     * @return
     */
    CommentResponse addComment(CommentRequest commentRequest);





}
