package com.food.recipe.api.repository.post;

import com.food.recipe.api.entity.post.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

/**
 * Created by Semih, 1.10.2023
 */
@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
//    Optional<PostEntity> findById(Long postId);
//
//    Page<PostEntity> findByCreatedBy(Long userId, Pageable pageable);
//
//    List<PostEntity> findByCreatedBy(Long userId);
//
//    long countByCreatedBy(Long userId);

//    @Query("SELECT p from Post p where p.createdBy in :followingUsersIds")
//    Page<PostEntity> findAllPostsByFollowedUsers(@Param("followingUsersIds") List<Long> followingUsersIds, Pageable pageable);
}
