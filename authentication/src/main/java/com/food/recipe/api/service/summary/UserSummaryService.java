package com.food.recipe.api.service.summary;

import com.food.recipe.api.model.response.summary.UserSummaryResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 1.10.2023
 */

@Service
public interface UserSummaryService {

    UserSummaryResponse summary(String username);
}
