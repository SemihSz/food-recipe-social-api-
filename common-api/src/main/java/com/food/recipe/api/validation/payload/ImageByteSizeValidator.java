package com.food.recipe.api.validation.payload;

import com.food.recipe.api.validation.annotation.ValidImageSizeByte;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Semih, 1.10.2023
 */
public class ImageByteSizeValidator implements ConstraintValidator<ValidImageSizeByte, byte[]> {

    private int maxWidth;
    private int maxHeight;
    private int maxFileSizeMB;

    @Override
    public void initialize(ValidImageSizeByte constraintAnnotation) {
        this.maxWidth = constraintAnnotation.maxWidth();
        this.maxHeight = constraintAnnotation.maxHeight();
        this.maxFileSizeMB = constraintAnnotation.maxFileSizeMB();
    }

    @Override
    public boolean isValid(byte[] imageData, ConstraintValidatorContext context) {
        return validateImageSize(imageData) && validateFileSize(imageData);
    }

    private boolean validateImageSize(byte[] imageData) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
            BufferedImage image = ImageIO.read(inputStream);

            int width = image.getWidth();
            int height = image.getHeight();

            return width <= maxWidth && height <= maxHeight;
        } catch (IOException e) {
            return false; // Resim okuma hatası
        }
    }

    private boolean validateFileSize(byte[] imageData) {
        // Byte dizisinin boyutunu MB cinsinden hesapla
        long fileSizeMB = imageData.length / (1024 * 1024);

        return fileSizeMB <= maxFileSizeMB;
    }
}
