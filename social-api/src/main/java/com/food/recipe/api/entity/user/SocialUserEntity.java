package com.food.recipe.api.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

/**
 * Created by Semih, 1.10.2023
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SocialUserEntity {

    @Id
    private Long id;

    private String username;

    private String name;

    private String surname;

    private boolean isPrivate;

    private String url;

    private String bioHeader;

    private String bioDesc;

    private String gender;

    private String phone;

    private String email;

    private Long profileImageId;

    private Long postCount;

    private Long followersCount;

    private Long followedCount;

    private Long numberOfNotifications;

    private Long numberOfRt;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;
}
