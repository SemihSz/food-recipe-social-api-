package com.food.recipe.api.service.logger;

import com.food.recipe.api.model.RestClientRequest;
import com.food.recipe.api.model.logger.SaveLogRequest;
import com.food.recipe.api.service.client.DocumentServiceRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

import static com.food.recipe.api.Constant.URL.Logger.LOGGER_SAVE;

/**
 * Created by Semih, 19.10.2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoggerService implements Consumer<SaveLogRequest> {

    private final DocumentServiceRestClient<Boolean> documentServiceRestClientDocument;

    @Override
    public void accept(SaveLogRequest saveLogRequest) {

        final RestClientRequest restClientRequest = RestClientRequest.builder()
                .url("http://localhost:9818".concat(LOGGER_SAVE))
                .requestMethod(HttpMethod.POST)
                .body(saveLogRequest)
                .build();

        documentServiceRestClientDocument.apply(restClientRequest, Boolean.class);
    }
}
