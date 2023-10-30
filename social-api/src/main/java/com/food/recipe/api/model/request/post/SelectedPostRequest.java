package com.food.recipe.api.model.request.post;

import com.food.recipe.api.model.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectedPostRequest extends BaseRequest {

    private Long postId;
}
