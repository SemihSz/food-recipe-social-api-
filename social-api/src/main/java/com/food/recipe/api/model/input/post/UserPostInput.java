package com.food.recipe.api.model.input.post;

import com.food.recipe.api.entity.user.SocialUserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPostInput {

    private SocialUserEntity user;
}
