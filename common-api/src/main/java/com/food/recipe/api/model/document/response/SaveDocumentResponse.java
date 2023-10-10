package com.food.recipe.api.model.document.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 9.10.2023
 */
@Getter
@Setter
@Builder
public class SaveDocumentResponse {

    private Long documentId;

    private Long userId;

    private String fileName;
}
