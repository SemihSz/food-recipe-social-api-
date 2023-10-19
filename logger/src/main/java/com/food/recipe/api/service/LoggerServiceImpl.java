package com.food.recipe.api.service;

import com.food.recipe.api.model.logger.SaveLogRequest;
import com.food.recipe.api.service.executable.SaveLogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * Created by Semih, 18.10.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoggerServiceImpl implements LoggerService {

    private final SaveLogService saveLogService;

    @Override
    public Boolean saveLog(SaveLogRequest request)  {
        try {
            return saveLogService.apply(request).get();
        }
        catch ( ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return Boolean.FALSE;
    }
}
