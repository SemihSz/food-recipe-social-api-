package com.food.recipe.api.model.logger;

import com.food.recipe.api.model.enums.ApplicationEnums;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Created by Semih, 18.10.2023
 */
@Data
@Builder
public class SaveLogRequest {

    private ApplicationEnums application;

    private String method;

    private String url;

    private String requestBody;

    private String response;

    private Map<String, String> headers;

    private int responseCode;

    private long startTime;

    private long timeTaken;


}
