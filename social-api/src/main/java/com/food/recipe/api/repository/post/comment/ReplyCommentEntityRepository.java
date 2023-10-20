package com.food.recipe.api.repository.post.comment;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.comment.CommentsEntity;
import com.food.recipe.api.entity.post.comment.ReplyCommentEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyCommentEntityRepository extends JpaRepository<ReplyCommentEntity, Long> {

    @Query("SELECT r FROM ReplyCommentEntity r " +
            "WHERE r.comment = :comment AND r.user = :user AND r.post = :post")
    List<ReplyCommentEntity> findAllByCommentAndUserAndPost(
            @Param("comment") CommentsEntity comment,
            @Param("user") SocialUserEntity user,
            @Param("post") PostEntity post
    );
}