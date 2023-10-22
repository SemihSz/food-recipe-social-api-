package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.request.recipe.CreateRecipeRequest;
import com.food.recipe.api.model.response.recipe.CreateRecipeResponse;
import com.food.recipe.api.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/create-new")
    public ResponseEntity<RestResponse<CreateRecipeResponse>> createPost(@RequestBody CreateRecipeRequest createRecipeRequest) {

        return ResponseEntity.ok(null);
    }

}
