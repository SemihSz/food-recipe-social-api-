package com.food.recipe.api.model.response;

import com.food.recipe.api.model.DocumentInfoDTO;
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
