package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.request.comment.reply.BaseReplyCommentRequest;
import com.food.recipe.api.model.request.comment.reply.UpdateReplyCommentRequest;
import com.food.recipe.api.model.response.comment.reply.ReplyCommentResponse;
import com.food.recipe.api.service.ReplyCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply/comment")
@RequiredArgsConstructor
public class ReplyCommentController {

  private final ReplyCommentService replyCommentService;

  @PostMapping("/add")
  public ResponseEntity<RestResponse<ReplyCommentResponse>> addComment(@RequestBody BaseReplyCommentRequest createCommentRequest) {

    return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, replyCommentService.addComment(createCommentRequest)));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<RestResponse<ReplyCommentResponse>> deleteComment(@RequestBody BaseReplyCommentRequest commentDeleteRequest) {

    return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, replyCommentService.deleteComment(commentDeleteRequest)));
  }

  @PutMapping("/update")
  public ResponseEntity<RestResponse<ReplyCommentResponse>> updateComment(@RequestBody UpdateReplyCommentRequest commentUpdateRequest) {

    return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, replyCommentService.updateComment(commentUpdateRequest)));
  }
}
