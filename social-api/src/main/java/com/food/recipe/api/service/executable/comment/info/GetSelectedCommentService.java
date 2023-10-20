package com.food.recipe.api.service.executable.comment.info;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.comment.CommentsEntity;
import com.food.recipe.api.entity.user.SocialUserEntity;
import com.food.recipe.api.model.comment.CommentList;
import com.food.recipe.api.model.request.comment.PostCommentRequest;
import com.food.recipe.api.repository.post.comment.CommentsRepository;
import com.food.recipe.api.repository.user.SocialUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetSelectedCommentService implements SimpleTask<PostCommentRequest, List<CommentList>> {

    private final CommentsRepository commentsRepository;

    private final SocialUserRepository socialUserRepository;

    @Override
    public List<CommentList> apply(PostCommentRequest postCommentRequest) {

        final Long postId = postCommentRequest.getPostId();
        final List<CommentsEntity> comments = commentsRepository.findCommentsByPostId(postId);
        final List<CommentList> commentLists = new ArrayList<>();
        if (!CollectionUtils.isEmpty(comments)) {

            for (CommentsEntity comment : comments) {
                final Optional<SocialUserEntity> userInfo = socialUserRepository.findById(comment.getId());
                if (userInfo.isEmpty()) {
                    final SocialUserEntity getUserInfo = userInfo.get();
                    final CommentList item = CommentList.builder()
                            .userId(getUserInfo.getId())
                            .username(getUserInfo.getUsername())
                            .name(getUserInfo.getName())
                            .surname(getUserInfo.getSurname())
                            .createdAt(comment.getCreatedAt())
                            .updatedAt(comment.getUpdateAt())
                            .commentDescription(comment.getBody())
                            .replyComments(null) // All comment check beloved reply comments.
                            .build();

                    commentLists.add(item);
                }
            }

            return commentLists;

        }

        return null;
    }
}
