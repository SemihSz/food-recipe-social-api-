package com.food.recipe.api.model.document.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Semih, 9.10.2023
 */
@Getter
@Setter
@NoArgsConstructor
public class SaveDocumentResponse implements Serializable {

    private Long documentId;

    private Long userId;

    private String fileName;
}
