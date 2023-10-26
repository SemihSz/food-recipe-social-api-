package com.food.recipe.api.service.impl;

import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.mapper.CreateRecipeInputMapper;
import com.food.recipe.api.model.input.recipe.CreateRecipeInput;
import com.food.recipe.api.model.request.like.LikedBaseRequest;
import com.food.recipe.api.model.request.recipe.CreateRecipeRequest;
import com.food.recipe.api.model.request.recipe.DeleteRecipeRequest;
import com.food.recipe.api.model.request.recipe.UpdateRecipeRequest;
import com.food.recipe.api.model.response.recipe.CreateRecipeResponse;
import com.food.recipe.api.service.RecipeService;
import com.food.recipe.api.service.executable.recipe.CreateNewRecipeService;
import com.food.recipe.api.service.executable.user.GetSocialAppUserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final GetSocialAppUserInfoService getSocialAppUserInfoService;

    private final CreateNewRecipeService createNewRecipeService;

    @Override
    public CreateRecipeResponse createNewRecipe(CreateRecipeRequest request) {

        // Retrieve the social user information based on the user ID and username from the request
        final SocialUserEntity socialUserEntity = getSocialAppUserInfoService.apply(request.getId(), request.getUsername());
        final CreateRecipeInput input = CreateRecipeInputMapper.INSTANCE.convert(request, socialUserEntity);

        return createNewRecipeService.apply(input);
    }

    @Override
    public CreateRecipeResponse updatedRecipe(UpdateRecipeRequest request) {
        return null;
    }

    @Override
    public Boolean deleteRecipe(DeleteRecipeRequest request) {
        return null;
    }

    @Override
    public void likes(LikedBaseRequest request) {

    }

    @Override
    public void dislikes(LikedBaseRequest request) {

    }

    @Override
    public void commentRecipe() {

    }
}
