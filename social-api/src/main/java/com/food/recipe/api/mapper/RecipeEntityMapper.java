package com.food.recipe.api.mapper;

import com.food.recipe.api.entity.recipe.RecipeEntity;
import com.food.recipe.api.model.input.recipe.CreateRecipeInput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeEntityMapper {

  RecipeEntityMapper INSTANCE = Mappers.getMapper(RecipeEntityMapper.class);

  RecipeEntity convert(CreateRecipeInput input);

}
