package com.food.recipe.api.entity.post;

import com.food.recipe.api.entity.recipe.RecipeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

/**
 * Created by Semih, 1.10.2023
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    private String description;

    private String imageName;

    @ElementCollection
    private List<Long> imageId;

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "recipe_entity.id", nullable = false)
    private RecipeEntity recipe;
}
