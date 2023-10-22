package com.food.recipe.api.model.request.recipe;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateRecipeRequest extends CreateRecipeRequest {

    private Long recipeId;
}
