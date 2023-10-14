package com.food.recipe.api.repository.post.comment.like;

import com.food.recipe.api.entity.post.comment.like.CommentLikedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikedEntityRepository extends JpaRepository<CommentLikedEntity, Long> {
}