package com.food.recipe.api.service.executable.recipe;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.recipe.RecipeEntity;
import com.food.recipe.api.mapper.RecipeEntityMapper;
import com.food.recipe.api.model.input.recipe.CreateRecipeInput;
import com.food.recipe.api.model.response.recipe.CreateRecipeResponse;
import com.food.recipe.api.repository.recipe.RecipeEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateNewRecipeService implements SimpleTask<CreateRecipeInput, CreateRecipeResponse> {

  private final RecipeEntityRepository repository;

  @Override
  public CreateRecipeResponse apply(CreateRecipeInput input) {

    final RecipeEntity recipe = RecipeEntityMapper.INSTANCE.convert(input);
    repository.save(recipe);

    return CreateRecipeResponse.builder().recipeId(recipe.getId()).build();
  }
}
