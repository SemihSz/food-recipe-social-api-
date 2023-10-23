package com.food.recipe.api.mapper;

import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.input.recipe.CreateRecipeInput;
import com.food.recipe.api.model.request.recipe.CreateRecipeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateRecipeInputMapper {

  CreateRecipeInputMapper INSTANCE = Mappers.getMapper(CreateRecipeInputMapper.class);
  CreateRecipeInput convert(CreateRecipeRequest request, SocialUserEntity user);

}
