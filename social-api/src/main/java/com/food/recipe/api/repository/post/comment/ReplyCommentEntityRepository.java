package com.food.recipe.api.repository.post.comment;

import com.food.recipe.api.entity.post.comment.ReplyCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyCommentEntityRepository extends JpaRepository<ReplyCommentEntity, Long> {
}