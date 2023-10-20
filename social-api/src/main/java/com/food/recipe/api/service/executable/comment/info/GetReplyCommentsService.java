package com.food.recipe.api.service.executable.comment.info;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.comment.CommentEntity;
import com.food.recipe.api.entity.post.comment.ReplyCommentEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.comment.ReplyCommentList;
import com.food.recipe.api.model.input.comment.ReplyCommentInput;
import com.food.recipe.api.repository.post.comment.ReplyCommentEntityRepository;
import com.food.recipe.api.service.executable.user.GetUserInfoWithIdService;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class GetReplyCommentsService implements SimpleTask<ReplyCommentInput, List<ReplyCommentList>> {

    private final ReplyCommentEntityRepository replyCommentEntityRepository;

    private final GetCommentInformationService getCommentInformationService;

    private final GetUserInfoWithIdService getUserInfoWithIdService;

    @Override
    public List<ReplyCommentList> apply(ReplyCommentInput replyCommentInput) {

        final List<ReplyCommentEntity> replyCommentEntityList = replyCommentEntityRepository.findAllByCommentAndUserAndPost(replyCommentInput.getComment(),
                replyCommentInput.getSocialUserInfo(), replyCommentInput.getPost());

        final List<ReplyCommentList> replyCommentList = new ArrayList<>();

        for (ReplyCommentEntity entity : replyCommentEntityList) {

            final CommentEntity commentInformation = getCommentInformationService.apply(entity.getId());
            final SocialUserEntity socialUser = getUserInfoWithIdService.apply(entity.getUser().getId());

            final ReplyCommentList replyComment = ReplyCommentList.builder()
                .commentDescription(commentInformation.getBody())
                .postId(entity.getPost().getId())
                .userId(socialUser.getId())
                .name(socialUser.getName())
                .surname(socialUser.getSurname())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();

            replyCommentList.add(replyComment);
        }

        return replyCommentList;
    }
}
