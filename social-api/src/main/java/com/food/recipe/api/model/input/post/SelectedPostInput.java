package com.food.recipe.api.model.input.post;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectedPostInput {

    private PostEntity post;

    private SocialUserEntity user;
}
