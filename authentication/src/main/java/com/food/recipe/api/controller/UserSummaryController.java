package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.response.summary.UserSummaryResponse;
import com.food.recipe.api.service.summary.UserSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Semih, 1.10.2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/summary")
public class UserSummaryController {

    private final UserSummaryService summaryService;

    @GetMapping("/info/{username}")
    public ResponseEntity<RestResponse<UserSummaryResponse>> documentList(@PathVariable String username) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, summaryService.summary(username)));
    }
}
