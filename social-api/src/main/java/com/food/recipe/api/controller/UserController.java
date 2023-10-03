package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.request.user.RegisterUserSocialAppRequest;
import com.food.recipe.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Semih, 1.10.2023
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RestResponse<Boolean>> createPost(@RequestBody RegisterUserSocialAppRequest request) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, userService.registerUser(request)));
    }
}
