package com.food.recipe.api.model;

import com.food.recipe.api.model.properties.ProjectInfoModel;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Semih, 5.10.2023
 */
@ConfigurationProperties("project-info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProjectInfosProperties {

  private List<ProjectInfoModel> packages;

}
