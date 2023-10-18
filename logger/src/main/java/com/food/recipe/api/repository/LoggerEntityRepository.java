package com.food.recipe.api.repository;

import com.food.recipe.api.entity.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerEntityRepository extends JpaRepository<LoggerEntity, Long> {
}