package com.food.recipe.api.repository.post;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.SavedPostEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Semih, 1.10.2023
 */
@Repository
public interface SavedPostRepository extends JpaRepository<SavedPostEntity, Long> {
//    @Query("SELECT p FROM SavedPost p where p.post.id = :postId and p.user.id = :userId")
//    Optional<SavedPostEntity> findSavedPostByPostIdByUserId(@Param("userId") Long userId, @Param("postId") Long postId);
//
//    List<SavedPostEntity> findByCreatedBy(Long userId);
    List<SavedPostEntity> findByUser(SocialUserEntity entity);

    SavedPostEntity findByUserAndPost(SocialUserEntity entity, PostEntity post);
}
