package com.food.recipe.api.repository.post.comment;

import com.food.recipe.api.entity.post.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 1.10.2023
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

//    @Query("SELECT COUNT(v.id) from Comment v where v.post.id = :postId")
//    long countByPostId(@Param("postId") Long postId);
//
//    Page<Comment> findCommentsByPostId(@Param("postId") Long postId, Pageable pageable);
//
//    List<Comment> findCommentsByPostId(@Param("postId") Long postId);
//
//    @Query("SELECT c FROM Comment c where c.id = :commentId and c.post.id = :postId")
//    Optional<Comment> findCommentByPostIdAndCommentId(@Param("postId") Long postId, @Param("commentId") Long commentId);
}
