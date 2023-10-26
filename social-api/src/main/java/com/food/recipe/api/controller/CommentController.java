package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.comment.CommentList;
import com.food.recipe.api.model.request.comment.CommentDeleteRequest;
import com.food.recipe.api.model.request.comment.CommentUpdateRequest;
import com.food.recipe.api.model.request.comment.CreateCommentRequest;
import com.food.recipe.api.model.request.comment.PostCommentRequest;
import com.food.recipe.api.model.request.like.LikeRecipeRequest;
import com.food.recipe.api.model.request.like.LikedBaseRequest;
import com.food.recipe.api.model.response.comment.CommentResponse;
import com.food.recipe.api.model.response.comment.SelectedPostCommentsResponse;
import com.food.recipe.api.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Semih, 15.10.2023
 */

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<RestResponse<CommentResponse>> addComment(@RequestBody CreateCommentRequest createCommentRequest) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, commentService.addComment(createCommentRequest)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<RestResponse<CommentResponse>> deleteComment(@RequestBody CommentDeleteRequest commentDeleteRequest) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, commentService.deleteComment(commentDeleteRequest)));
    }

    @PutMapping("/update")
    public ResponseEntity<RestResponse<CommentResponse>> updateComment(@RequestBody CommentUpdateRequest commentUpdateRequest) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, commentService.updateComment(commentUpdateRequest)));
    }

    @GetMapping("/selected-post-comments")
    public ResponseEntity<RestResponse<List<CommentList>>> selectedPostComments(@RequestBody PostCommentRequest postCommentRequest) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, commentService.selectedPostComments(postCommentRequest)));
    }

    @GetMapping("/like")
    public ResponseEntity like(@RequestBody LikedBaseRequest request) {

        commentService.likes(request);
        return ResponseEntity.ok().build();
    }

}
