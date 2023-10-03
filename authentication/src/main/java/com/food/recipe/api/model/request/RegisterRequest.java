package com.food.recipe.api.model.request;

import com.food.recipe.api.enums.RoleTypes;
import com.food.recipe.api.validation.annotation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 30.09.2023
 */
@Getter
@Setter
public class RegisterRequest {

    private String username;

    @ValidPassword
    private String password;

    @ValidPassword
    private String rePassword;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Email
    private String email;

    private boolean isPrivate;

    private String bioHeader;

    private String bioDesc;

    private String url;

    private String gender;

    private String phone;

    private String profilePhotoId;

    private RoleTypes role;
}
