package com.food.recipe.api.controller;

import com.food.recipe.api.model.request.AuthLoginRequest;
import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.request.ChangePasswordRequest;
import com.food.recipe.api.model.request.RegisterRequest;
import com.food.recipe.api.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RestResponse<Boolean>> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, authService.registerUser(registerRequest)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequest authRequest) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, authService.login(authRequest)));
    }
    @PostMapping("/password/change")
    public ResponseEntity<RestResponse<Boolean>> passwordChange(@Valid @RequestBody ChangePasswordRequest request) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, authService.changePassword(request)));
    }

}
