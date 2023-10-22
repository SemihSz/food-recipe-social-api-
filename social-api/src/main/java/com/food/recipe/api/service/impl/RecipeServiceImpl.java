package com.food.recipe.api.service.impl;

import com.food.recipe.api.model.request.recipe.CreateRecipeRequest;
import com.food.recipe.api.model.request.recipe.DeleteRecipeRequest;
import com.food.recipe.api.model.request.recipe.UpdateRecipeRequest;
import com.food.recipe.api.model.response.recipe.CreateRecipeResponse;
import com.food.recipe.api.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    @Override
    public CreateRecipeResponse createNewRecipe(CreateRecipeRequest request) {
        return null;
    }

    @Override
    public CreateRecipeResponse updatedRecipe(UpdateRecipeRequest request) {
        return null;
    }

    @Override
    public Boolean deleteRecipe(DeleteRecipeRequest request) {
        return null;
    }

    @Override
    public void likeRecipe() {

    }

    @Override
    public void commentRecipe() {

    }
}
