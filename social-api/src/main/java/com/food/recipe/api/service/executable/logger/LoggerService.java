package com.food.recipe.api.service.executable.logger;

import com.food.recipe.api.model.RestClientRequest;
import com.food.recipe.api.model.enums.ApplicationEnums;
import com.food.recipe.api.model.logger.SaveLogRequest;
import com.food.recipe.api.model.properties.ProjectInfoModel;
import com.food.recipe.api.service.client.ServiceRestClient;
import com.food.recipe.api.service.executable.config.GetProjectInfoService;
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

    private final ServiceRestClient<Boolean> serviceRestClient;

    private final GetProjectInfoService getProjectInfoService;

    @Override
    public void accept(SaveLogRequest saveLogRequest) {

        final ProjectInfoModel infoModel = getProjectInfoService.apply(ApplicationEnums.LOGGER.name());

        final RestClientRequest restClientRequest = RestClientRequest.builder()
                .url(infoModel.getUrl().concat(LOGGER_SAVE))
                .requestMethod(HttpMethod.POST)
                .body(saveLogRequest)
                .build();

        serviceRestClient.apply(restClientRequest, Boolean.class);
    }
}
