package com.food.recipe.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Semih, 7.10.2023
 */
@Getter
@Setter
@Builder
public class RestClientRequest implements Serializable {

    private String url;

    private MultiValueMap<String, String> body;

    private Map<String, Object> queryParams;

    private Map<String, Object> pathVariables;

    private HttpMethod requestMethod;

    private HttpHeaders httpHeaders;
}
