package com.food.recipe.api.exception;

import com.food.recipe.api.model.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Created by Semih, 3.07.2023
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> businessError(BusinessException ex) {

        final ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
