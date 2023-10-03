package com.food.recipe.api.validation.annotation;

import com.food.recipe.api.validation.payload.ImageStringSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Created by Semih, 1.10.2023
 */
@Documented
@Constraint(validatedBy = ImageStringSizeValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImageSizeString {
    String message() default "Invalid image size";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int maxWidth() default Integer.MAX_VALUE; // Maksimum genişlik
    int maxHeight() default Integer.MAX_VALUE; // Maksimum yükseklik
}
