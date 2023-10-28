package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.document.Base64Files;
import com.food.recipe.api.model.request.BaseRequest;
import com.food.recipe.api.model.request.post.GetUserPostRequest;
import com.food.recipe.api.model.request.post.PostRequest;
import com.food.recipe.api.model.response.post.CreatePostResponse;
import com.food.recipe.api.model.response.post.UserPostResponse;
import com.food.recipe.api.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Semih, 1.10.2023
 */

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/create-post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse<CreatePostResponse>> createPost(@RequestBody @Valid PostRequest postRequest) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, postService.createPost(postRequest)));
    }

    @PostMapping(value = "/convert-base64", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestResponse<List<Base64Files>>> convertBase64(@RequestParam("file") MultipartFile[] file) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, postService.convertMultipartBase64(file)));
    }


    @PostMapping(value = "/create-post-via-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestResponse<CreatePostResponse>> createPostWithMultiPartFile(@RequestParam("file") MultipartFile[] file,
                                                                                        @RequestParam("username") String username,
                                                                                        @RequestParam("id") Long id) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, postService.createPostViaFile(file, username, id)));
    }

    @GetMapping( "/list")
    public ResponseEntity<RestResponse<UserPostResponse>> userPostList(@RequestBody GetUserPostRequest request) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, postService.userPosts(request)));
    }
}
