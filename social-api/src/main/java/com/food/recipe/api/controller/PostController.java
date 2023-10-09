package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.request.post.PostRequest;
import com.food.recipe.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Semih, 1.10.2023
 */

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create-post")
    public ResponseEntity<RestResponse<Boolean>> createPost(@RequestBody PostRequest postRequest) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, postService.createPost(postRequest)));
    }
}
