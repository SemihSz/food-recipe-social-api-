package com.food.recipe.api.model.request;

import com.food.recipe.api.validation.annotation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 1.10.2023
 */

@Getter
@Setter
public class ChangePasswordRequest extends AuthRequest {

    @ValidPassword
    private String oldPassword;

    @ValidPassword
    private String newPassword;

    @ValidPassword
    private String newRePassword;
}
