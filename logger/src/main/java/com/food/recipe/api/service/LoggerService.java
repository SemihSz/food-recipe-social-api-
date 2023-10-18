package com.food.recipe.api.service;

import com.food.recipe.api.model.SaveLogRequest;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 18.10.2023
 */
@Service
public interface LoggerService {

    Boolean saveLog(SaveLogRequest request);


}
