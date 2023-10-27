package com.food.recipe.api.entity.post.comment.like;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.comment.CommentsEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.enums.LikeEnums;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDislikeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private LikeEnums dislikeTypes;

  @ManyToOne(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
  @JoinColumn(name = "post_entity.id", nullable = false)
  private PostEntity post;

  @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
  @JoinColumn(name = "social_user_entity.id", nullable = false)
  private SocialUserEntity user;

  @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
  @JoinColumn(name = "comment_entity.id", nullable = false)
  private CommentsEntity comment;

}
