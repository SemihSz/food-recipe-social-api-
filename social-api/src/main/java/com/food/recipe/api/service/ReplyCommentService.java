package com.food.recipe.api.service;

import com.food.recipe.api.model.request.comment.CommentDeleteRequest;
import com.food.recipe.api.model.request.comment.CommentUpdateRequest;
import com.food.recipe.api.model.request.comment.CreateCommentRequest;
import com.food.recipe.api.model.response.comment.reply.ReplyCommentResponse;
import org.springframework.stereotype.Service;

@Service
public interface ReplyCommentService {

  /**
   *
   * @param createCommentRequest
   * @return CommentResponse
   */
  ReplyCommentResponse addComment(CreateCommentRequest createCommentRequest);

  /**
   * @param commentUpdateRequest
   * @return CommentResponse
   */
  ReplyCommentResponse updateComment(CommentUpdateRequest commentUpdateRequest);

  /**
   *
   * @param commentDeleteRequest
   * @return CommentResponse
   */
  ReplyCommentResponse deleteComment(CommentDeleteRequest commentDeleteRequest);
}
