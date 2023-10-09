package com.food.recipe.api.service.executable;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.exception.BusinessException;
import com.food.recipe.api.model.request.user.RegisterUserSocialAppRequest;
import com.food.recipe.api.repository.user.SocialUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.food.recipe.api.Constant.Exception.*;

/**
 * Created by Semih, 1.10.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterUserSocialAppService implements SimpleTask<RegisterUserSocialAppRequest, Boolean> {

    private final SocialUserRepository socialUserRepository;

    private final MessageSource messageSource;

    @Override
    public Boolean apply(RegisterUserSocialAppRequest request) {

        final Optional<SocialUserEntity> socialUserEntity = socialUserRepository.findById(request.getId());

        if (socialUserEntity.isEmpty()) {
            final UUID randomUUID = UUID.randomUUID();
            final SocialUserEntity createNewUser = SocialUserEntity.builder()
                    .id(request.getId())
                    .uuid(randomUUID.toString())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .username(request.getUsername())
                    .name(request.getName())
                    .surname(request.getSurname())
                    .isPrivate(request.isAccountStatus())
                    .phone(request.getPhone())
                    .bioHeader(request.getBioHeader())
                    .bioDesc(request.getBioDesc())
                    .url(request.getUrl())
                    .followedCount(0L)
                    .followersCount(0L)
                    .postCount(0L)
                    .numberOfRt(0L)
                    .numberOfNotifications(0L)
                    .build();

            socialUserRepository.save(createNewUser);
            return Boolean.TRUE;
        }
        throw new BusinessException((messageSource.getMessage(USER_EXIST, null, Locale.ENGLISH)));

    }
}
