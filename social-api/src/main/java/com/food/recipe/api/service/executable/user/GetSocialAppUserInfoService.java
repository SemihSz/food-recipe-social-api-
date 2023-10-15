package com.food.recipe.api.service.executable.user;

import com.food.recipe.api.Mappers;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.repository.user.SocialUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 15.10.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GetSocialAppUserInfoService implements Mappers<Long, String, SocialUserEntity> {

    private final SocialUserRepository socialUserRepository;

    @Override
    public SocialUserEntity apply(Long id, String username) {
        return socialUserRepository.getUser(username, id);
    }
}
