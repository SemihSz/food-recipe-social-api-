package com.food.recipe.api.service.auth;

import com.food.recipe.api.model.request.AuthLoginRequest;
import com.food.recipe.api.model.request.ChangePasswordRequest;
import com.food.recipe.api.model.response.JwtResponse;
import com.food.recipe.api.model.request.RegisterRequest;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 30.09.2023
 */
@Service
public interface AuthService {

    Boolean registerUser(RegisterRequest registerRequest);

    JwtResponse login(AuthLoginRequest authLoginRequest);

    Boolean changePassword(ChangePasswordRequest request);
}
