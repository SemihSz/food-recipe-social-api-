package com.food.recipe.api.service.executable.config;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.model.ProjectInfosProperties;
import com.food.recipe.api.model.properties.ProjectInfoModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProjectInfoService implements SimpleTask<String, ProjectInfoModel> {

  private final ProjectInfosProperties projectInfosProperties;

  @Override
  public ProjectInfoModel apply(String s) {
    return projectInfosProperties.getPackages().stream().filter(t -> t.getName().equals(s)).findFirst().get();
  }
}
