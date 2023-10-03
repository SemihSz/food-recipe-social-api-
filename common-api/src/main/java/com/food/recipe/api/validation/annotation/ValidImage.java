package com.food.recipe.api.validation.annotation;

import com.food.recipe.api.validation.payload.ImageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Created by Semih, 1.10.2023
 */
@Documented
@Constraint(validatedBy = ImageValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImage {

    String message() default "Invalid image!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
