package com.food.recipe.api.model.response.post;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostResponse {

    private Long id;

    private String description;

    private String imageName;

    private List<PostImage> imageResponse;
}
