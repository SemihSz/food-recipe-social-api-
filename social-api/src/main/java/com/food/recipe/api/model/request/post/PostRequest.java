package com.food.recipe.api.model.request.post;

import com.food.recipe.api.model.document.Base64Files;
import com.food.recipe.api.model.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Semih, 1.10.2023
 */
@Getter
@Setter
public class PostRequest extends BaseRequest {

    private String description;

    private String base64StringImage;

    private List<Base64Files> base64ImageList;

    private byte[] byteArrayImage;

}
