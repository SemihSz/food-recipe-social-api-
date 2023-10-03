package com.food.recipe.api.service;

import com.food.recipe.api.model.request.user.RegisterUserSocialAppRequest;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 1.10.2023
 */
@Service
public interface UserService {

    Boolean registerUser(RegisterUserSocialAppRequest request);
}
