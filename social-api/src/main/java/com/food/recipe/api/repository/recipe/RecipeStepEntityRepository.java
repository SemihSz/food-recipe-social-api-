package com.food.recipe.api.repository.recipe;

import com.food.recipe.api.entity.recipe.RecipeStepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeStepEntityRepository extends JpaRepository<RecipeStepEntity, Long> {

}