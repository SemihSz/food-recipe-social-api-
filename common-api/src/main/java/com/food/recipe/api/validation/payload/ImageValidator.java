package com.food.recipe.api.validation.payload;

import com.food.recipe.api.validation.annotation.ValidImage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Semih, 1.10.2023
 */
public class ImageValidator implements ConstraintValidator<ValidImage, String> {

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif", "heic", "heif");

    @Override
    public void initialize(ValidImage constraintAnnotation) {
    }

    @Override
    public boolean isValid(String fileName, ConstraintValidatorContext context) {
        return validateImageExtension(fileName);
    }

    private boolean validateImageExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false; // Dosya adı boş veya null ise geçersiz
        }

        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return false; // Uzantı yoksa geçersiz
        }

        String extension = fileName.substring(lastDotIndex + 1).toLowerCase();
        return ALLOWED_EXTENSIONS.contains(extension);
    }
}
