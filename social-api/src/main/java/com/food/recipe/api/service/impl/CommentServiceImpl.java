package com.food.recipe.api.service.impl;

import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.comment.CommentsEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.comment.CommentList;
import com.food.recipe.api.model.enums.LikeEnums;
import com.food.recipe.api.model.input.comment.AddCommentInput;
import com.food.recipe.api.model.input.like.LikeInput;
import com.food.recipe.api.model.request.comment.CommentDeleteRequest;
import com.food.recipe.api.model.request.comment.CommentUpdateRequest;
import com.food.recipe.api.model.request.comment.CreateCommentRequest;
import com.food.recipe.api.model.request.comment.PostCommentRequest;
import com.food.recipe.api.model.request.like.LikedBaseRequest;
import com.food.recipe.api.model.response.comment.CommentResponse;
import com.food.recipe.api.service.CommentService;
import com.food.recipe.api.service.LikeDislikeAbstractService;
import com.food.recipe.api.service.executable.comment.AddCommentService;
import com.food.recipe.api.service.executable.comment.DeleteCommentService;
import com.food.recipe.api.service.executable.comment.info.GetCommentInformationService;
import com.food.recipe.api.service.executable.comment.UpdateCommentService;
import com.food.recipe.api.service.executable.comment.info.GetSelectedCommentService;
import com.food.recipe.api.service.executable.like.SaveLikeService;
import com.food.recipe.api.service.executable.post.GetPostInformationService;
import com.food.recipe.api.service.executable.user.GetSocialAppUserInfoService;
import java.util.List;
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

    private final GetPostInformationService getPostInformationService;

    private final GetSocialAppUserInfoService getSocialAppUserInfoService;

    private final GetCommentInformationService getCommentInformationService;

    private final GetSelectedCommentService getSelectedCommentService;

    private final AddCommentService addCommentService;

    private final UpdateCommentService updateCommentService;

    private final DeleteCommentService deleteCommentService;

    private final SaveLikeService saveLikeService;

    /**
     * Add comment service layer
     * @param createCommentRequest
     * @return
     */
    @Override
    public CommentResponse addComment(CreateCommentRequest createCommentRequest) {

        final SocialUserEntity socialUserEntity = getSocialAppUserInfoService.apply(createCommentRequest.getUserId(), createCommentRequest.getUsername());
        final PostEntity getPostInformation = getPostInformationService.apply(createCommentRequest.getPostId());

        if (Objects.nonNull(socialUserEntity) && Objects.nonNull(getPostInformation)) {

            final AddCommentInput commentInput = AddCommentInput.builder()
                    .description(createCommentRequest.getDescription())
                    .post(getPostInformation)
                    .user(socialUserEntity)
                    .build();

            return addCommentService.apply(commentInput);
        }

        return null;
    }

    /**
     * Updated comment service layer
     * @param commentUpdateRequest
     * @return
     */
    @Override
    public CommentResponse updateComment(CommentUpdateRequest commentUpdateRequest) {

        final CommentsEntity comment = getCommentInformationService.apply(commentUpdateRequest.getCommentId());

        if (Objects.nonNull(comment)) {
            final boolean isUpdatedComment = updateCommentService.test(commentUpdateRequest, comment);

            if (Boolean.TRUE.equals(isUpdatedComment)) {
                return CommentResponse.builder().commentId(comment.getId()).isUpdated(Boolean.TRUE).build();
            }
        }

        return null;
    }

    /**
     * Delete comment information layer
     * @param commentDeleteRequest
     * @return
     */
    @Override
    public CommentResponse deleteComment(CommentDeleteRequest commentDeleteRequest) {

        final CommentsEntity comment = getCommentInformationService.apply(commentDeleteRequest.getCommentId());

        if (Objects.nonNull(comment)) {
            final boolean isDeletedComment = deleteCommentService.test(comment);

            if (Boolean.TRUE.equals(isDeletedComment)) {
                return CommentResponse.builder().isDeleted(Boolean.TRUE).build();
            }
        }

        return null;
    }

    @Override
    public List<CommentList> selectedPostComments(PostCommentRequest postCommentRequest) {
        return getSelectedCommentService.apply(postCommentRequest);
    }

    @Override
    public void likes(LikedBaseRequest request) {

        final SocialUserEntity socialUserEntity = getSocialAppUserInfoService.apply(request.getId(), request.getUsername());
        final PostEntity getPostInformation = getPostInformationService.apply(request.getPostId());
        final CommentsEntity comment = getCommentInformationService.apply(request.getCommentId());

        if (Objects.nonNull(socialUserEntity) && Objects.nonNull(getPostInformation) && Objects.nonNull(comment)) {

            final LikeInput input = LikeInput.builder()
                .commentId(comment.getId())
                .LikeTypes(LikeEnums.COMMENT_LIKES)
                .user(socialUserEntity)
                .comment(comment)
                .post(getPostInformation)
                .build();

            saveLikeService.accept(input);
        }
    }

    @Override
    public void dislikes(LikedBaseRequest request) {

    }


}
