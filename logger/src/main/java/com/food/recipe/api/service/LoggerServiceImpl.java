package com.food.recipe.api.service;

import com.food.recipe.api.model.SaveLogRequest;
import com.food.recipe.api.service.executable.SaveLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 18.10.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoggerServiceImpl implements LoggerService {

    private final SaveLogService saveLogService;

    @Override
    public Boolean saveLog(SaveLogRequest request) {
        return saveLogService.apply(request);
    }
}
