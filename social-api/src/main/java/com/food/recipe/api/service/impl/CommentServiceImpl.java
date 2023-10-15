package com.food.recipe.api.service.impl;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.input.comment.AddCommentInput;
import com.food.recipe.api.model.request.comment.CommentRequest;
import com.food.recipe.api.model.response.comment.CommentResponse;
import com.food.recipe.api.repository.post.comment.CommentRepository;
import com.food.recipe.api.service.CommentService;
import com.food.recipe.api.service.executable.comment.AddCommentService;
import com.food.recipe.api.service.executable.post.GetPostInformationService;
import com.food.recipe.api.service.executable.user.GetSocialAppUserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Semih, 15.10.2023
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final GetPostInformationService getPostInformationService;

    private final GetSocialAppUserInfoService getSocialAppUserInfoService;

    private final AddCommentService addCommentService;

    @Override
    public CommentResponse addComment(CommentRequest commentRequest) {

        final SocialUserEntity socialUserEntity = getSocialAppUserInfoService.apply(commentRequest.getUserId(), commentRequest.getUsername());
        final PostEntity getPostInformation = getPostInformationService.apply(commentRequest.getPostId());

        if (Objects.nonNull(socialUserEntity) && Objects.nonNull(getPostInformation)) {

            final AddCommentInput commentInput = AddCommentInput.builder()

                    .description(commentRequest.getDescription())
                    .post(getPostInformation)
                    .user(socialUserEntity)
                    .build();

            return addCommentService.apply(commentInput);
        }

        return null;
    }
}