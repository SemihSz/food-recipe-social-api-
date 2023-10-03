package com.food.recipe.api.repository.user;

import com.food.recipe.api.entity.user.SocialUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 1.10.2023
 */
@Repository
public interface SocialUserRepository extends JpaRepository<SocialUserEntity, Long> {
}
