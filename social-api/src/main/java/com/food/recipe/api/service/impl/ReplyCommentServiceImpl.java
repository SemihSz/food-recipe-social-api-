package com.food.recipe.api.service.impl;

import com.food.recipe.api.model.request.comment.CommentDeleteRequest;
import com.food.recipe.api.model.request.comment.CommentUpdateRequest;
import com.food.recipe.api.model.request.comment.CreateCommentRequest;
import com.food.recipe.api.model.response.comment.reply.ReplyCommentResponse;
import com.food.recipe.api.service.ReplyCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyCommentServiceImpl implements ReplyCommentService {

  @Override
  public ReplyCommentResponse addComment(CreateCommentRequest createCommentRequest) {
    return null;
  }

  @Override
  public ReplyCommentResponse updateComment(CommentUpdateRequest commentUpdateRequest) {
    return null;
  }

  @Override
  public ReplyCommentResponse deleteComment(CommentDeleteRequest commentDeleteRequest) {
    return null;
  }
}
