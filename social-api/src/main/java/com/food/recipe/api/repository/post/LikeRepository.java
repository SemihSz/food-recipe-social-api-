package com.food.recipe.api.repository.post;

import com.food.recipe.api.entity.post.LikeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Semih, 1.10.2023
 */
@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
//    @Query("SELECT l FROM Like l where l.post.id = :postId and l.user.id = :userId")
//    Optional<LikeEntity> findLikeByPostIdByUserId(@Param("userId") Long userId, @Param("postId") Long postId);
//
//    Page<LikeEntity> findByPostId(Long postId, Pageable
//            pageable);
//
//    @Query("SELECT COUNT(l.id) from Like l where l.post.id = :postId")
//    long countByPostId(@Param("postId") Long postId);
}
