package com.food.recipe.api.interceptor;

import com.food.recipe.api.model.logger.ApplicationEnums;
import com.food.recipe.api.model.logger.SaveLogRequest;
import com.food.recipe.api.service.logger.LoggerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Semih, 4.10.2023
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingFilterBean extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilterBean.class);

    private final LoggerService loggerService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long timeTaken = System.currentTimeMillis() - startTime;

        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
                request.getCharacterEncoding());
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
                response.getCharacterEncoding());

        // HTTP başlıkları
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headerInfoRequest = new HashMap<>();
        List<String> headerInfoResponse = new ArrayList<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headerInfoRequest.put(headerName, headerValue);
            LOGGER.info("Request Header: {} = {}", headerName, headerValue);
        }

        // Yanıt başlıkları
        Collection<String> responseHeaderNames = responseWrapper.getHeaderNames();
        for (String headerName : responseHeaderNames) {
            String headerValue = responseWrapper.getHeader(headerName);
            headerInfoResponse.add(headerValue);
            LOGGER.info("Response Header: {} = {}", headerName, headerValue);
        }

        LOGGER.info(
                "FINISHED PROCESSING : METHOD={}; REQUEST_URI={};  REQUEST PAYLOAD={}; RESPONSE CODE={}; RESPONSE={}; TIM TAKEN={}",
                request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody,
                timeTaken);

        final SaveLogRequest saveLogRequest = SaveLogRequest.builder()
                .headers(headerInfoRequest)
                .application(ApplicationEnums.DOCUMENT)
                .url(request.getRequestURI())
                .method(request.getMethod())
                .requestBody(requestBody)
                .response(responseBody)
                .responseCode(response.getStatus())
                .startTime(startTime)
                .timeTaken(timeTaken)
                .build();

        loggerService.accept(saveLogRequest);

        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}

