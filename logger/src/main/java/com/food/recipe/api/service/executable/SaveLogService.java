package com.food.recipe.api.service.executable;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.LoggerEntity;
import com.food.recipe.api.model.SaveLogRequest;
import com.food.recipe.api.repository.LoggerEntityRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by Semih, 18.10.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SaveLogService implements SimpleTask<SaveLogRequest, Boolean> {

    private final LoggerEntityRepository loggerEntityRepository;

    private final Gson gson;

    @Override
    public Boolean apply(SaveLogRequest request) {

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
                    .build();

            loggerEntityRepository.save(saveLogItem);

            return Boolean.TRUE;
        }
        catch (Exception e) {
            log.error("Save Operation Fails");
        }
        return Boolean.FALSE;
    }
}
