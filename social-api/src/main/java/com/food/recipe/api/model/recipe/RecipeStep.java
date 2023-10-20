package com.food.recipe.api.model.recipe;

import lombok.Data;

@Data
public class RecipeStep {

  private int stepNumber;

  private String title;

  private String description;

  private String imageBase64;

}
