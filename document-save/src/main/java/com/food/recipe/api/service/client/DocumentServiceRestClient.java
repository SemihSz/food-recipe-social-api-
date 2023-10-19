package com.food.recipe.api.service.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.food.recipe.api.Mappers;
import com.food.recipe.api.exception.BusinessException;
import com.food.recipe.api.model.RestClientRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Semih, 7.10.2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceRestClient<T> implements Mappers<RestClientRequest, Class<T>, T> {

    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;


    @Override
    public T apply(RestClientRequest request, Class<T> responseModel) {

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        try {
            if (HttpMethod.POST.equals(request.getRequestMethod())) {
                // Bu bir POST isteği ise, gerekli değişiklikleri yapın
                final ResponseEntity<T> response = restTemplate.exchange(request.getUrl(), HttpMethod.POST, new HttpEntity<>(request.getBody(),
                        request.getHttpHeaders()), responseModel);
                if (response.getStatusCode() == HttpStatus.OK) {
                    String someJsonString = mapper.writeValueAsString(response.getBody());
                    return mapper.readValue(someJsonString, responseModel);
                }
                else {
                    errorHandler(response);
                }
            } else {
                // GET isteği olarak devam edin
                final UriComponentsBuilder builder = uriBuilder(request.getUrl(), request.getQueryParams());
                if (request.getPathVariables() != null) {
                    builder.uriVariables(request.getPathVariables());
                }
                final ResponseEntity<T> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(request.getHttpHeaders()),
                        responseModel, request.getPathVariables());
                if (response.getStatusCode() == HttpStatus.OK) {
                    String someJsonString = mapper.writeValueAsString(response.getBody());
                    return mapper.readValue(someJsonString, responseModel);
                }
                else {
                    errorHandler(response);
                }
            }
        } catch (RestClientException e) {
            // TODO Fix here so many hardcoded area
            log.error("RestClientException occurred while calling {}: {}", request.getUrl(), e.getMessage());
            if (e instanceof HttpClientErrorException && ((HttpClientErrorException) e).getStatusCode() == HttpStatus.UNAUTHORIZED) {
                log.error("HTTP 401 Unauthorized!");
                throw new BusinessException("HTTP 401 Unauthorized!");
            } else {
                log.error("Beklenmeyen bir HTTP hatası oluştu: " + e.getMessage());
            }
            return null;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private UriComponentsBuilder uriBuilder(String url, Map<String, Object> queryParams) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (Objects.nonNull(queryParams)) {
            Set<String> sets = queryParams.keySet();
            for (String key : sets) {
                builder.queryParam(key, queryParams.get(key));
            }
        }
        return builder;
    }

    public void errorHandler(ResponseEntity<T> response) {
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            log.error("HTTP Request failed with status code: {}", response.getStatusCode());
            // Optionally, you can log the response body or other details here.
            log.error("Response Body: {}", response.getBody());
            log.error("Response Headers: {}", response.getHeaders());

            // TODO throw here exception
            // You can also return a custom error message or throw an exception.
            // throw new MyCustomException("HTTP Request failed with status code: " + response.getStatusCode());
        }
    }
}
