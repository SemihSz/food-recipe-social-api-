package com.food.recipe.api.service.executable.user;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.repository.user.SocialUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserInfoWithIdService implements SimpleTask<Long, SocialUserEntity> {

  private final SocialUserRepository socialUserRepository;

  @Override
  public SocialUserEntity apply(Long userId) {

    return socialUserRepository.findById(userId).orElse(null);
  }
}
