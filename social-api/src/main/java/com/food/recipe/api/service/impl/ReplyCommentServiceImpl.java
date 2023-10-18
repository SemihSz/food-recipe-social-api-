package com.food.recipe.api.service.impl;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.comment.CommentEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.input.comment.AddReplyCommentInput;
import com.food.recipe.api.model.request.comment.reply.BaseReplyCommentRequest;
import com.food.recipe.api.model.request.comment.reply.UpdateReplyCommentRequest;
import com.food.recipe.api.model.response.comment.reply.ReplyCommentResponse;
import com.food.recipe.api.service.ReplyCommentService;
import com.food.recipe.api.service.executable.comment.GetCommentInformationService;
import com.food.recipe.api.service.executable.comment.reply.AddReplyCommentService;
import com.food.recipe.api.service.executable.post.GetPostInformationService;
import com.food.recipe.api.service.executable.user.GetSocialAppUserInfoService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyCommentServiceImpl implements ReplyCommentService {

  private final GetPostInformationService getPostInformationService;

  private final GetSocialAppUserInfoService getSocialAppUserInfoService;

  private final GetCommentInformationService getCommentInformationService;

  private final AddReplyCommentService addReplyCommentService;


/**
 * Adds a reply comment to a post based on the provided request.
 *
 * @param request The request containing the necessary information to add the reply comment.
 * @return The response indicating the result of adding the reply comment.
 *         Returns null if any of the required information is missing or invalid.
 */
@Override
public ReplyCommentResponse addComment(BaseReplyCommentRequest request) {

  // Retrieve the social user information based on the user ID and username from the request
  final SocialUserEntity socialUserEntity = getSocialAppUserInfoService.apply(request.getUserId(), request.getUsername());

  // Retrieve the post information based on the post ID from the request
  final PostEntity getPostInformation = getPostInformationService.apply(request.getPostId());

  // Retrieve the comment information based on the comment ID from the request
  final CommentEntity comment = getCommentInformationService.apply(request.getCommentId());

  // Check if all the required information is available and the description is not empty
  if (Objects.nonNull(comment) && Objects.nonNull(getPostInformation) &&
      Objects.nonNull(socialUserEntity) && !StringUtils.isEmpty(request.getDescription())) {
    // Create an input object for adding the reply comment
    final AddReplyCommentInput input = AddReplyCommentInput.builder()
        .comment(comment)
        .post(getPostInformation)
        .socialUserInfo(socialUserEntity)
        .replyCommentBody(request.getDescription())
        .build();

    // Add the reply comment using the input object and return the response
    return addReplyCommentService.apply(input);
  }

  // Return null if any of the required information is missing or invalid
  return null;
}

  @Override
  public ReplyCommentResponse updateComment(UpdateReplyCommentRequest request) {
    return null;
  }

  @Override
  public ReplyCommentResponse deleteComment(BaseReplyCommentRequest request) {
    return null;
  }
}
