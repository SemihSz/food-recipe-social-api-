package com.food.recipe.api.entity;

import com.food.recipe.api.model.logger.ApplicationEnums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * Created by Semih, 18.10.2023
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoggerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplicationEnums application;

    private String method;

    private String url;

    @Column(columnDefinition = "text")
    private String requestBody;

    @Column(columnDefinition = "text")
    private String response;

    @Column(columnDefinition = "text")
    private String headers;

    private int responseCode;

    private long startTime;

    private long timeTaken;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
