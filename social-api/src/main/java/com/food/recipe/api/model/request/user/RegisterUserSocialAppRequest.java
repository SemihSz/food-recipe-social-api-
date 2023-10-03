package com.food.recipe.api.model.request.user;

import com.food.recipe.api.model.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 1.10.2023
 */
@Getter
@Setter
public class RegisterUserSocialAppRequest extends BaseRequest {

    private String name;

    private String surname;

    private boolean isPrivate;

    private String url;

    private String bioHeader;

    private String bioDesc;

    private String gender;

    private String phone;

    private String email;

}
