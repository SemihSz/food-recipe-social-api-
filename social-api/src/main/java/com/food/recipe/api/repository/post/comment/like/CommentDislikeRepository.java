package com.food.recipe.api.repository.post.comment.like;

import com.food.recipe.api.entity.post.comment.like.CommentDislikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDislikeRepository extends JpaRepository<CommentDislikeEntity, Long> {

}