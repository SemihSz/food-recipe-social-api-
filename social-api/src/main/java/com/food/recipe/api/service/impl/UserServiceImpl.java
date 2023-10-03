package com.food.recipe.api.service.impl;

import com.food.recipe.api.model.request.user.RegisterUserSocialAppRequest;
import com.food.recipe.api.service.UserService;
import com.food.recipe.api.service.executable.RegisterUserSocialAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 1.10.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final RegisterUserSocialAppService registerUserSocialAppService;

    @Override
    public Boolean registerUser(RegisterUserSocialAppRequest request) {
        return null;
    }
}
