package com.food.recipe.api.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Aspect
@Component
@RequiredArgsConstructor
public class TokenAspect {

    private final HttpServletRequest request;

    private final RestTemplate restTemplate;

    @Before("execution(* com.food.recipe.api.controller.*.*(..))")
    public void validateToken() {
        String token = request.getHeader("Authorization");
        if (token == null || !isValid("TODO URL WRITE", token)) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
    }

    public boolean isValid(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        final ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        final HttpStatusCode statusCode = response.getStatusCode();

        return statusCode == HttpStatus.OK;
    }
}
