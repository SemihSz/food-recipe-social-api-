package com.food.recipe.api.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class TokenAspect {

    private final HttpServletRequest request;

    private final RestTemplate restTemplate;

    public static final String USER_ID = "USER_ID";

    public static final String USERNAME = "USERNAME";

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && execution(* com.food.recipe.api.controller.*.*(..))")
    public void allMethods() {
    }

 /*   @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && execution(* com.crypto.controller.*..*(..))")
    public void allMethods() {
    }*/


    @Around(" allMethods()")
    public Object validateAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        final Method method = signature.getMethod();

        final String userId = request.getHeader(USER_ID);
        final String username = request.getHeader(USERNAME);

        String token = request.getHeader("Authorization");
        if (token == null || !isValid("http://localhost:9898/summary/info/", token, userId, username)) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        return proceedingJoinPoint.proceed();
    }


    public boolean isValid(String url, String token, String userId, String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("USER_ID", userId);
        headers.set("Authorization", token);

        final ResponseEntity<String> response = restTemplate.exchange(url.concat(username), HttpMethod.GET, new HttpEntity<>(headers), String.class);
        final HttpStatusCode statusCode = response.getStatusCode();

        return statusCode == HttpStatus.OK;
    }
}
