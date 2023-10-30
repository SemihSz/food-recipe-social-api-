package com.food.recipe.api.service.executable.post;

import com.food.recipe.api.Constant;
import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.SavedPostEntity;
import com.food.recipe.api.mapper.post.PostImageMapper;
import com.food.recipe.api.model.RestClientRequest;
import com.food.recipe.api.model.document.response.DocumentInfoResponse;
import com.food.recipe.api.model.enums.ApplicationEnums;
import com.food.recipe.api.model.input.post.SelectedPostInput;
import com.food.recipe.api.model.properties.ProjectInfoModel;
import com.food.recipe.api.model.response.post.PostImage;
import com.food.recipe.api.model.response.post.SelectedPostResponse;
import com.food.recipe.api.repository.post.SavedPostRepository;
import com.food.recipe.api.repository.post.comment.CommentsRepository;
import com.food.recipe.api.service.client.ServiceRestClient;
import com.food.recipe.api.service.executable.config.GetProjectInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetSelectedPostService implements SimpleTask<SelectedPostInput, SelectedPostResponse> {
    private final CommentsRepository commentsRepository;

    private final GetProjectInfoService getProjectInfoService;

    private final SavedPostRepository savedPostRepository;

    private final ServiceRestClient<DocumentInfoResponse> serviceRestClient;

    @Override
    public SelectedPostResponse apply(SelectedPostInput input) {

        final ProjectInfoModel infoModel = getProjectInfoService.apply(ApplicationEnums.DOCUMENT.name());

        final SavedPostEntity postEntity = savedPostRepository.findByUserAndPost(input.getUser(), input.getPost());

        if (Objects.nonNull(postEntity)) {

            final List<PostImage> postImageList = new ArrayList<>();
            for (Long imageId : postEntity.getPost().getImageId()) {
                Map<String, Object> pathVariable = new HashMap<>();
                pathVariable.put("documentId", imageId);


                final RestClientRequest<?> request = RestClientRequest.builder()
                        .requestMethod(HttpMethod.GET)
                        .pathVariables(pathVariable)
                        .url(infoModel.getUrl().concat(Constant.URL.Document.SELECTED_DOCUMENT_INFO))
                        .build();

                final DocumentInfoResponse response = serviceRestClient.apply(request, DocumentInfoResponse.class);

                final PostImage image = PostImageMapper.INSTANCE.convert(response.getDocument());
                postImageList.add(image);
            }

            long commentCount = commentsRepository.countByPost(postEntity.getPost());

            return SelectedPostResponse.builder()
                    .id(postEntity.getPost().getId())
                    .imageResponse(postImageList)
                    .description(postEntity.getPost().getDescription())
                    .commentCounts(commentCount)
                    .build();
        }

        return null;
    }
}
