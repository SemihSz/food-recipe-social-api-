package com.food.recipe.api.aspect;

import com.food.recipe.api.Constant;
import com.food.recipe.api.model.RestClientRequest;
import com.food.recipe.api.service.client.ServiceRestClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class TokenAspect {

    public static final String USER_ID = "USER_ID";

    public static final String USERNAME = "USERNAME";

    private final ServiceRestClient<Object> serviceRestClient;

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
        if (token == null || !isValid(token, userId, username)) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        return proceedingJoinPoint.proceed();
    }


    public boolean isValid (String token, String userId, String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("USER_ID", userId);
        headers.set("Authorization", token);
        headers.set("username", username);

        Map<String, Object> path = new HashMap<>();
        path.put("username", username);

        final RestClientRequest restClientRequest = RestClientRequest.builder()
                .url("http://localhost:9898".concat(Constant.URL.AUTH_INFO))
                .requestMethod(HttpMethod.GET)
                .httpHeaders(headers)
                .pathVariables(path)
                .build();

        return Objects.nonNull(serviceRestClient.apply(restClientRequest, Object.class));
    }
}
