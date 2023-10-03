package com.food.recipe.api.service.executable;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.request.user.RegisterUserSocialAppRequest;
import com.food.recipe.api.repository.user.SocialUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

/**
 * Created by Semih, 1.10.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterUserSocialAppService implements SimpleTask<RegisterUserSocialAppRequest, Boolean> {

    private final SocialUserRepository socialUserRepository;

    @Override
    public Boolean apply(RegisterUserSocialAppRequest request) {

        final Optional<SocialUserEntity> socialUserEntity = socialUserRepository.findById(request.getId());

        if (socialUserEntity.isEmpty()) {

            final SocialUserEntity createNewUser = SocialUserEntity.builder()
                    .id(request.getId())
                    .createdAt(Instant.now())
                    .username(request.getUsername())
                    .name(request.getName())
                    .surname(request.getSurname())
                    .isPrivate(request.isPrivate())
                    .phone(request.getPhone())
                    .bioHeader(request.getBioHeader())
                    .bioDesc(request.getBioDesc())
                    .url(request.getUrl())
                    .build();

            socialUserRepository.save(createNewUser);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
