package com.food.recipe.api.model.input.recipe;

import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.recipe.RecipeStep;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRecipeInput {


  private String title;

  private String info;

  private String cuisineType;

  private String mealType;

  private String preparationTime;

  private String cookTime;

  private String totalTime;

  private int servings;

  private int caloriesPerServing;

  private List<String> ingredients;

  private List<RecipeStep> instructions;

  private SocialUserEntity user;
}
