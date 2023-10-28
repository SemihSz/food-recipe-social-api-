package com.food.recipe.api.model.response.post;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserPostResponse {

    private List<PostResponse> posts;
}
