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

  @Override
  public ReplyCommentResponse addComment(BaseReplyCommentRequest request) {

    final SocialUserEntity socialUserEntity = getSocialAppUserInfoService.apply(request.getUserId(), request.getUsername());
    final PostEntity getPostInformation = getPostInformationService.apply(request.getPostId());
    final CommentEntity comment = getCommentInformationService.apply(request.getCommentId());

    if (Objects.nonNull(comment) && Objects.nonNull(getPostInformation) &&
        Objects.nonNull(socialUserEntity) && !StringUtils.isEmpty(request.getDescription())) {

      final AddReplyCommentInput input = AddReplyCommentInput.builder()
          .comment(comment)
          .post(getPostInformation)
          .socialUserInfo(socialUserEntity)
          .replyCommentBody(request.getDescription())
          .build();

      return addReplyCommentService.apply(input);
    }

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
