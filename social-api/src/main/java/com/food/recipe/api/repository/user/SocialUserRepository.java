package com.food.recipe.api.repository.user;

import com.food.recipe.api.entity.user.SocialUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 1.10.2023
 */
@Repository
public interface SocialUserRepository extends JpaRepository<SocialUserEntity, Long> {

    @Query(value = "SELECT * FROM social_user_entity t WHERE t.username=:username AND t.id=:id",nativeQuery = true)
    SocialUserEntity getUser(@Param("username") String username, @Param("id") Long id);
}
