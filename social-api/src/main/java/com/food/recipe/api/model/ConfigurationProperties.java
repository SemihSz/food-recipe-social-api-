package com.food.recipe.api.model;

import com.food.recipe.api.model.properties.ProjectInfoModel;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by Semih, 5.10.2023
 */
@org.springframework.boot.context.properties.ConfigurationProperties("projects")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ConfigurationProperties {

  private Map<String, ProjectInfoModel> projects;

}
