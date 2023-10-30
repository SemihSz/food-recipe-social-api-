package com.food.recipe.api.model.document.response;

import com.food.recipe.api.model.document.DocumentInfoDTO;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Semih, 3.07.2023
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentInfoResponse implements Serializable {

    private DocumentInfoDTO document;
}
