package com.food.recipe.api.model.request.recipe;

import com.food.recipe.api.model.recipe.RecipeStep;
import com.food.recipe.api.model.request.BaseRequest;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateRecipeRequest extends BaseRequest {

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

}
