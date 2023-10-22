package com.food.recipe.api.entity.post;

import com.food.recipe.api.entity.recipe.RecipeEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.enums.LikeEnums;
import jakarta.persistence.*;
import lombok.*;

/**
 * Created by Semih, 1.10.2023
 */
@Entity
//@Table(name = "likes", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {
//                "post_id",
//                "user_id"
//        })
//})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LikeEntity {
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
    @JoinColumn(name = "recipe_entity.id", nullable = false)
    private RecipeEntity recipe;
}
