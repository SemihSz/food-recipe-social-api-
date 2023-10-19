package com.food.recipe.api.service.executable;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.LoggerEntity;
import com.food.recipe.api.model.logger.SaveLogRequest;
import com.food.recipe.api.repository.LoggerEntityRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Semih, 18.10.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SaveLogService implements SimpleTask<SaveLogRequest, CompletableFuture<Boolean>> {

    private final LoggerEntityRepository loggerEntityRepository;

    private final Gson gson;

    @Async
    @Override
    public CompletableFuture<Boolean> apply(SaveLogRequest request) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        try {
            final LoggerEntity saveLogItem = LoggerEntity.builder()
                    .headers(gson.toJson(request.getHeaders()))
                    .requestBody(gson.toJson(request.getRequestBody()))
                    .url(request.getUrl())
                    .method(request.getMethod())
                    .responseCode(request.getResponseCode())
                    .startTime(request.getStartTime())
                    .timeTaken(request.getTimeTaken())
                    .response(gson.toJson(request.getResponse()))
                    .createdAt(LocalDateTime.now())
                    .application(request.getApplication())
                    .build();

            loggerEntityRepository.save(saveLogItem);

            future.complete(Boolean.TRUE);
        } catch (Exception e) {
            log.error("Save Operation Fails", e);
            future.complete(Boolean.FALSE);
        }
        return future;
    }
}
