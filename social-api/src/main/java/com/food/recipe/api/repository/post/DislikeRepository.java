package com.food.recipe.api.repository.post;

import com.food.recipe.api.entity.post.DislikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DislikeRepository extends JpaRepository<DislikeEntity, Long> {

}