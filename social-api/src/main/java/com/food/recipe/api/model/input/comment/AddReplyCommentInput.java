package com.food.recipe.api.model.input.comment;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.comment.CommentsEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddReplyCommentInput {

  private String replyCommentBody;

  private CommentsEntity comment;

  private PostEntity post;

  private SocialUserEntity socialUserInfo;

}
