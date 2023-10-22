package com.food.recipe.api.entity.recipe;

import com.food.recipe.api.entity.user.SocialUserEntity;
import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class RecipeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "cuisine_type")
  private String cuisineType;

  @Column(name = "meal_type")
  private String mealType;

  @Column(name = "preparation_time")
  private String preparationTime;

  @Column(name = "cook_time")
  private String cookTime;

  @Column(name = "total_time")
  private String totalTime;

  @Column(name = "servings")
  private int servings;

  @Column(name = "calories_per_serving")
  private int caloriesPerServing;

  @ElementCollection
  @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
  @Column(name = "ingredient")
  private List<String> ingredients;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "recipe_id")
  private List<RecipeStepEntity> instructions;

  @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
  @JoinColumn(name = "social_user_entity.id", nullable = false)
  private SocialUserEntity user;

}
