package com.food.recipe.api.repository.post.comment;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.comment.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Semih, 1.10.2023
 */
@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {

    //    @Query("SELECT COUNT(v.id) from Comment v where v.post.id = :postId")
//    long countByPostId(@Param("postId") Long postId);
//
//    Page<Comment> findCommentsByPostId(@Param("postId") Long postId, Pageable pageable);
//
//    @Query(value = "SELECT * FROM CommentEntity c WHERE c.post.id = :postId", nativeQuery = true)
//    List<CommentsEntity> findCommentsByPostId(@Param("postId") Long postId);

    List<CommentsEntity> findByPost(PostEntity post);
    long countByPost(PostEntity post);

//
//    @Query("SELECT c FROM Comment c where c.id = :commentId and c.post.id = :postId")
//    Optional<Comment> findCommentByPostIdAndCommentId(@Param("postId") Long postId, @Param("commentId") Long commentId);
}
