package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.request.comment.CommentRequest;
import com.food.recipe.api.model.response.comment.CommentResponse;
import com.food.recipe.api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/add-comment")
    public ResponseEntity<RestResponse<CommentResponse>> addComment(@RequestBody CommentRequest commentRequest) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, commentService.addComment(commentRequest)));
    }

}
