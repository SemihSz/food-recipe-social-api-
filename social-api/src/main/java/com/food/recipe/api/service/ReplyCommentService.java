package com.food.recipe.api.service;

import com.food.recipe.api.model.request.comment.reply.BaseReplyCommentRequest;
import com.food.recipe.api.model.request.comment.reply.UpdateReplyCommentRequest;
import com.food.recipe.api.model.response.comment.reply.ReplyCommentResponse;
import org.springframework.stereotype.Service;

@Service
public interface ReplyCommentService {

  /**
   *
   * @param request
   * @return ReplyCommentResponse
   */
  ReplyCommentResponse addComment(BaseReplyCommentRequest request);

  /**
   * @param request
   * @return ReplyCommentResponse
   */
  ReplyCommentResponse updateComment(UpdateReplyCommentRequest request);

  /**
   *
   * @param request
   * @return ReplyCommentResponse
   */
  ReplyCommentResponse deleteComment(BaseReplyCommentRequest request);
}
