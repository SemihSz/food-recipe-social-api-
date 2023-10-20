package com.food.recipe.api.validation.payload;

import com.food.recipe.api.validation.annotation.CustomExclude;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class CustomExcludePayload implements ExclusionStrategy {

  @Override
  public boolean shouldSkipField(FieldAttributes fieldAttributes) {
    return fieldAttributes.getAnnotation(CustomExclude.class) != null;
  }

  @Override
  public boolean shouldSkipClass(Class<?> aClass) {
    return false;
  }
}
