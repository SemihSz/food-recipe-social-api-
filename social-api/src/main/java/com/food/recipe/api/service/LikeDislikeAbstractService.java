package com.food.recipe.api.service;

import com.food.recipe.api.model.request.like.LikedBaseRequest;
import org.springframework.stereotype.Service;

@Service
public abstract class LikeDislikeAbstractService {

  public abstract void likes(LikedBaseRequest request);

  public abstract void dislikes(LikedBaseRequest request);

}
