package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.logger.SaveLogRequest;
import com.food.recipe.api.service.LoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Semih, 18.10.2023
 */

@RestController
@RequestMapping("/logger")
@RequiredArgsConstructor
public class LoggerController {

    private final LoggerService loggerService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> saveLog(@RequestBody SaveLogRequest saveLogRequest) {

        return ResponseEntity.ok().body(loggerService.saveLog(saveLogRequest));
    }
}
