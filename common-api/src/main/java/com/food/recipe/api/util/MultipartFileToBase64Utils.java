package com.food.recipe.api.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by Semih, 11.10.2023
 */
@Component
@NoArgsConstructor
public class MultipartFileToBase64Utils {

    public static String convert(MultipartFile[] files) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (MultipartFile file : files) {
            byte[] bytes = new byte[(int) file.getSize()];
            file.getInputStream().read(bytes);
            String base64String = Base64.getEncoder().encodeToString(bytes);
            sb.append(base64String);
        }
        return sb.toString();
    }

    public static String convert(MultipartFile file) throws IOException {
        byte[] bytes = new byte[(int) file.getSize()];
        file.getInputStream().read(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
