package com.food.recipe.api.model.recipe;

import com.food.recipe.api.model.document.Base64Files;
import lombok.Data;

@Data
public class RecipeStep {

  private int stepNumber;

  private String title;

  private String description;

  private Base64Files imageBase64;

  private Long imageId;

}
