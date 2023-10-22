package com.food.recipe.api.service;

import com.food.recipe.api.model.request.recipe.CreateRecipeRequest;
import com.food.recipe.api.model.response.recipe.CreateRecipeResponse;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService {

    CreateRecipeResponse createNewRecipe(CreateRecipeRequest createRecipeRequest);

    CreateRecipeResponse updatedRecipe();

    Boolean deleteRecipe();

    void likeRecipe();

    void commentRecipe();
}
