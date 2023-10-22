package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.request.recipe.CreateRecipeRequest;
import com.food.recipe.api.model.request.recipe.DeleteRecipeRequest;
import com.food.recipe.api.model.request.recipe.UpdateRecipeRequest;
import com.food.recipe.api.model.response.recipe.CreateRecipeResponse;
import com.food.recipe.api.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/create")
    public ResponseEntity<RestResponse<CreateRecipeResponse>> createNewRecipe(@RequestBody CreateRecipeRequest request) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, recipeService.createNewRecipe(request)));
    }

    @PostMapping("/update")
    public ResponseEntity<RestResponse<CreateRecipeResponse>> updateRecipe(@RequestBody UpdateRecipeRequest request) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, recipeService.updatedRecipe(request)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<RestResponse<Boolean>> deleteRecipe(@RequestBody DeleteRecipeRequest request) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, recipeService.deleteRecipe(request)));
    }

}
