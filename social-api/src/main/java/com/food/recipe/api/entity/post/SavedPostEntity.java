package com.food.recipe.api.entity.post;

import com.food.recipe.api.entity.user.SocialUserEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Created by Semih, 1.10.2023
 */
@Entity
//@Table(name = "saved_photos", uniqueConstraints = {
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
public class SavedPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private SocialUserEntity user;
}
