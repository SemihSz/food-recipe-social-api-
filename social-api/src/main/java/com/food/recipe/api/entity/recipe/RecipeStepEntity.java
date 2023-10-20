package com.food.recipe.api.entity.recipe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class RecipeStepEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "step_number")
  private int stepNumber;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "document_id")
  private Long documentId;

  @ManyToOne
  @JoinColumn(name = "recipe_id")
  private RecipeEntity recipe;

}

