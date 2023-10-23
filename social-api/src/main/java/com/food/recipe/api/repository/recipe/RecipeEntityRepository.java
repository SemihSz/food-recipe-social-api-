package com.food.recipe.api.repository.recipe;

import com.food.recipe.api.entity.recipe.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeEntityRepository extends JpaRepository<RecipeEntity, Long> {

}