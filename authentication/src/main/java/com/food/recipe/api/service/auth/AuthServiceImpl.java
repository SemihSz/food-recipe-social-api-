package com.food.recipe.api.service.auth;

import com.food.recipe.api.model.request.AuthLoginRequest;
import com.food.recipe.api.model.request.ChangePasswordRequest;
import com.food.recipe.api.model.request.RegisterRequest;
import com.food.recipe.api.model.response.JwtResponse;
import com.food.recipe.api.service.auth.change_password.ChangePasswordService;
import com.food.recipe.api.service.auth.login.LoginService;
import com.food.recipe.api.service.auth.register.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 30.09.2023
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RegisterService registerService;

    private final LoginService loginService;

    private final ChangePasswordService changePasswordService;

    @Override
    public Boolean registerUser(RegisterRequest registerRequest) {
        return registerService.apply(registerRequest);
    }

    @Override
    public JwtResponse login(AuthLoginRequest authLoginRequest) {
        return loginService.apply(authLoginRequest);
    }

    @Override
    public Boolean changePassword(ChangePasswordRequest request) {
        return changePasswordService.apply(request);
    }
}
