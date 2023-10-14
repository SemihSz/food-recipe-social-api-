package com.food.recipe.api.model.input;

import com.food.recipe.api.model.document.Base64Files;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Semih, 10.10.2023
 */
@Data
@Builder
public class SaveFileInput {

    private String fileName;

    private String fileType;

    private MultipartFile[] files;

    private byte[] byteArray;

    private String base64String;

    private List<Base64Files> base64StringList;

    private String username;

    private Long userId;
}
