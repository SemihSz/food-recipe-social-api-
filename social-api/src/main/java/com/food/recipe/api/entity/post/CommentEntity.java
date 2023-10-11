package com.food.recipe.api.entity.post;

import com.food.recipe.api.entity.user.SocialUserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Created by Semih, 1.10.2023
 */
@Entity
//@Table(name = "comments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String body;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "post_entity.id", nullable = false)
    private PostEntity post;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "social_user_entity.id", nullable = false)
    private SocialUserEntity user;
}
