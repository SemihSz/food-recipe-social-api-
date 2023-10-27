package com.food.recipe.api.service.executable.comment.info;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.comment.CommentsEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.comment.CommentList;
import com.food.recipe.api.model.comment.ReplyCommentList;
import com.food.recipe.api.model.input.comment.ReplyCommentInput;
import com.food.recipe.api.model.request.comment.PostCommentRequest;
import com.food.recipe.api.repository.post.comment.CommentsRepository;
import com.food.recipe.api.service.executable.post.GetPostInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetSelectedCommentService implements SimpleTask<PostCommentRequest, List<CommentList>> {

    private final CommentsRepository commentsRepository;

    private final GetPostInformationService getPostInformationService;

    private final GetReplyCommentsService getReplyCommentsService;

    @Override
    public List<CommentList> apply(PostCommentRequest postCommentRequest) {

        final Long postId = postCommentRequest.getPostId();
        // Retrieve the post information based on the post ID from the request
        final PostEntity getPostInformation = getPostInformationService.apply(postId);
        final List<CommentsEntity> comments = commentsRepository.findByPost(getPostInformation);
        final List<CommentList> commentLists = new ArrayList<>();
        if (!CollectionUtils.isEmpty(comments)) {

            for (CommentsEntity comment : comments) {
                final SocialUserEntity userInfo = comment.getUser();
                if (Objects.nonNull(userInfo)) {
                    final ReplyCommentInput replyCommentInput = ReplyCommentInput.builder()
                        .comment(comment)
                        .socialUserInfo(userInfo)
                        .post(getPostInformation)
                        .build();
                    final List<ReplyCommentList> replyCommentLists = getReplyCommentsService.apply(replyCommentInput);
                    final CommentList item = CommentList.builder()
                            .commentId(comment.getId())
                            .postId(postId)
                            .userId(userInfo.getId())
                            .username(userInfo.getUsername())
                            .name(userInfo.getName())
                            .surname(userInfo.getSurname())
                            .createdAt(comment.getCreatedAt())
                            .updatedAt(comment.getUpdateAt())
                            .commentDescription(comment.getBody())
                            .replyComments(replyCommentLists) // All comment check beloved reply comments.
                            .build();

                    commentLists.add(item);
                }
            }

            return commentLists;

        }

        return null;
    }
}
