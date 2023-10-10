package com.food.recipe.api.model.document_response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 3.07.2023
 */
@Getter
@Setter
@Builder
public class DocumentInfoResponse {

    private DocumentInfoDTO document;
}
