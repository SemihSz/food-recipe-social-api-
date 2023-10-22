package com.food.recipe.api.model.response.recipe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRecipeResponse {

    private Long recipeId;

}
