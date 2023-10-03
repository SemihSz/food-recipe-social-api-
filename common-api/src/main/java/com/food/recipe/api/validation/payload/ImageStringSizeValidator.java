package com.food.recipe.api.validation.payload;

import com.food.recipe.api.validation.annotation.ValidImageSizeString;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Semih, 1.10.2023
 */
public class ImageStringSizeValidator implements ConstraintValidator<ValidImageSizeString, String> {

    private int maxWidth;
    private int maxHeight;

    @Override
    public void initialize(ValidImageSizeString constraintAnnotation) {
        this.maxWidth = constraintAnnotation.maxWidth();
        this.maxHeight = constraintAnnotation.maxHeight();
    }

    @Override
    public boolean isValid(String fileName, ConstraintValidatorContext context) {
        return validateImageSize(fileName);
    }

    private boolean validateImageSize(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false; // Dosya adı boş veya null ise geçersiz
        }

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            BufferedImage image = ImageIO.read(inputStream);

            int width = image.getWidth();
            int height = image.getHeight();

            return width <= maxWidth && height <= maxHeight;
        } catch (IOException e) {
            return false; // Dosya okuma hatası
        }
    }
}
