package com.food.recipe.api.model.response.summary;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Semih, 1.10.2023
 */
@Data
@Builder
public class UserSummaryResponse {

    private Long id;

    private String username;

    private String name;

    private String imagePath;

    private String bioHeader;

    private String bioDesc;

    private boolean isAccountPrivate;
}
