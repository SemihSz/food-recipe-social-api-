package com.food.recipe.api.model.document.request;

import com.food.recipe.api.validation.annotation.CustomExclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by Semih, 3.07.2023
 */
@Getter
@Setter
@Builder
public class SaveDocumentRequest {

    private String username;

    private Long userId;

    @CustomExclude
    private MultipartFile file;

    @CustomExclude
    private MultipartFile[] files;
}
