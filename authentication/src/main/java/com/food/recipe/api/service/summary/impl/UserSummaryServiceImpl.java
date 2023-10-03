package com.food.recipe.api.service.summary.impl;

import com.food.recipe.api.entity.UserEntity;
import com.food.recipe.api.model.response.summary.UserSummaryResponse;
import com.food.recipe.api.service.info.UserInfoService;
import com.food.recipe.api.service.summary.UserSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Semih, 1.10.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserSummaryServiceImpl implements UserSummaryService {

    private final UserInfoService userInfoService;

    @Override
    public UserSummaryResponse summary(String username) {

        final UserEntity userInfo = userInfoService.apply(username);

        if (Objects.nonNull(userInfo)) {
            return UserSummaryResponse.builder()
                    .id(userInfo.getId())
                    .username(userInfo.getUsername())
                    .name(userInfo.getName())
                    .isAccountPrivate(userInfo.isPrivate())
                    .bioHeader(userInfo.getBioHeader())
                    .bioDesc(userInfo.getBioDesc())
                    .imagePath(userInfo.getProfileImageId())
                    .build();
        }

        return null;
    }
}
