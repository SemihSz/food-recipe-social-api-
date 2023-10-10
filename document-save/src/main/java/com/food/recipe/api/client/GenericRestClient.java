package com.food.recipe.api.client;

/**
 * Created by Semih, 6.10.2023
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class GenericRestClient {

    private final RestTemplate restTemplate;

    @Autowired
    public GenericRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> get(String url, Class<T> responseType, HttpHeaders headers, Map<String, String> queryParams, Map<String, Object> pathVariables) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        // Add query parameters
        if (queryParams != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        // Add path variables
        if (pathVariables != null) {
            builder.uriVariables(pathVariables);
        }

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(
                builder.build().toUri(),
                HttpMethod.GET,
                entity,
                responseType
        );

        return responseEntity;
    }

    public <T> ResponseEntity <T> post(String url, Class<T> responseType, Object requestObject, Map<String, String> pathVariables) {
        HttpHeaders headers = new HttpHeaders();

        // Add path variables
        if (pathVariables != null) {
            url = UriComponentsBuilder.fromHttpUrl(url).buildAndExpand(pathVariables).toUriString();
        }

        HttpEntity<Object> entity = new HttpEntity<>(requestObject, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                responseType
        );

        return responseEntity;
    }

    public <T> T put(String url, Class<T> responseType, Object requestObject, Map<String, String> pathVariables) {
        HttpHeaders headers = new HttpHeaders();

        // Add path variables
        if (pathVariables != null) {
            url = UriComponentsBuilder.fromHttpUrl(url).buildAndExpand(pathVariables).toUriString();
        }

        HttpEntity<Object> entity = new HttpEntity<>(requestObject, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                responseType
        );

        return responseEntity.getBody();
    }

    public void delete(String url, Map<String, String> pathVariables) {
        // Add path variables
        if (pathVariables != null) {
            url = UriComponentsBuilder.fromHttpUrl(url).buildAndExpand(pathVariables).toUriString();
        }

        restTemplate.delete(url);
    }
}

