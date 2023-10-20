package com.food.recipe.api.entity.recipe;

import com.food.recipe.api.entity.post.PostEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

}
