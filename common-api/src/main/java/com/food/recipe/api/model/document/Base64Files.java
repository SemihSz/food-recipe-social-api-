package com.food.recipe.api.model.document;

import com.food.recipe.api.validation.annotation.CustomExclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;

/**
 * Created by Semih, 3.07.2023
 */
@Getter
@Setter
@NoArgsConstructor
public class Base64Files {

    private String fileName;

    private String fileType;

    @CustomExclude
    private String base64Data;

    public Base64Files(String fileName, String fileType, String base64Data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.base64Data = base64Data;
    }


    public byte[] decodeBase64Data() {
        return Base64.getDecoder().decode(base64Data);
    }
}
