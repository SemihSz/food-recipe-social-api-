package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.response.summary.UserSummaryResponse;
import com.food.recipe.api.service.summary.UserSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Semih, 1.10.2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/summary")
public class UserSummaryController {

    private final UserSummaryService userSummaryService;

    @GetMapping("/info/{username}")
    public ResponseEntity<RestResponse<UserSummaryResponse>> documentListl(@PathVariable String username) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, userSummaryService.summary(username)));
    }

    @GetMapping("/info")
    public ResponseEntity<RestResponse<UserSummaryResponse>> documentList(@RequestHeader(value = "username") String username) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, userSummaryService.summary(username)));
    }
}
