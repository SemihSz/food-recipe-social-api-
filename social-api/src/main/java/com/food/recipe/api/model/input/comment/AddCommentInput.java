package com.food.recipe.api.model.input.comment;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Semih, 15.10.2023
 */
@Data
@Builder
public class AddCommentInput {

    private SocialUserEntity user;

    private PostEntity post;

    private String description;
}
