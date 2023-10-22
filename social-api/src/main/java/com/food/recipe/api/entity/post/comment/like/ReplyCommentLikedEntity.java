package com.food.recipe.api.entity.post.comment.like;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.comment.ReplyCommentEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.enums.LikeEnums;
import jakarta.persistence.*;
import lombok.*;

/**
 * Created by Semih, 15.10.2023
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReplyCommentLikedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LikeEnums LikeTypes;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "post_entity.id", nullable = false)
    private PostEntity post;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "social_user_entity.id", nullable = false)
    private SocialUserEntity user;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "reply_comment_entity.id", nullable = false)
    private ReplyCommentEntity replyComment;
}
