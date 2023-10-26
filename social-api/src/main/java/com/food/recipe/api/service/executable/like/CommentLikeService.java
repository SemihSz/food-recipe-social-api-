package com.food.recipe.api.service.executable.like;

import com.food.recipe.api.model.input.like.CommentLikeInput;
import com.food.recipe.api.repository.post.comment.like.CommentLikedEntityRepository;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentLikeService implements Consumer<CommentLikeInput> {

  private final CommentLikedEntityRepository commentLikedEntityRepository;
  @Override
  public void accept(CommentLikeInput input) {

  }
}
