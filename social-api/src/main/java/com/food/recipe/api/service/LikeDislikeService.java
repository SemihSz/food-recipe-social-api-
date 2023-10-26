package com.food.recipe.api.service;

import com.food.recipe.api.model.request.like.LikedBaseRequest;

public interface LikeDislikeService {

  void likes(LikedBaseRequest request);

  void dislikes(LikedBaseRequest request);


}
