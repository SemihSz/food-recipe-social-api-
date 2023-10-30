package com.food.recipe.api.service.executable.post;

import com.food.recipe.api.Constant.URL.Document;
import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.entity.post.SavedPostEntity;
import com.food.recipe.api.mapper.post.PostImageMapper;
import com.food.recipe.api.model.RestClientRequest;
import com.food.recipe.api.model.document.response.DocumentInfoResponse;
import com.food.recipe.api.model.enums.ApplicationEnums;
import com.food.recipe.api.model.input.post.UserPostInput;
import com.food.recipe.api.model.properties.ProjectInfoModel;
import com.food.recipe.api.model.response.post.PostImage;
import com.food.recipe.api.model.response.post.PostResponse;
import com.food.recipe.api.repository.post.SavedPostRepository;
import com.food.recipe.api.service.client.ServiceRestClient;
import com.food.recipe.api.service.executable.config.GetProjectInfoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetSelectedUserPostsService implements SimpleTask<UserPostInput, List<PostResponse>> {

    private final SavedPostRepository savedPostRepository;

    private final ServiceRestClient<DocumentInfoResponse> serviceRestClient;

    private final GetProjectInfoService getProjectInfoService;

    @Override
    public List<PostResponse> apply(UserPostInput userPostInput) {

        final List<SavedPostEntity> postsEntities = savedPostRepository.findByUser(userPostInput.getUser());

        final List<PostResponse> posts = new ArrayList<>();

        for (SavedPostEntity savedPost : postsEntities) {

            final PostEntity postEntity = savedPost.getPost();

            final List<Long> imageList = savedPost.getPost().getImageId();
            final ProjectInfoModel infoModel = getProjectInfoService.apply(ApplicationEnums.DOCUMENT.name());

            final List<PostImage> postImageList = new ArrayList<>();
            for (Long imageId : imageList) {
                Map<String, Object> pathVariable = new HashMap<>();
                pathVariable.put("documentId", imageId);

                final RestClientRequest<?> request = RestClientRequest.builder()
                    .requestMethod(HttpMethod.GET)
                    .pathVariables(pathVariable)
                    .url(infoModel.getUrl().concat(Document.SELECTED_DOCUMENT_INFO))
                    .build();

                final DocumentInfoResponse response = serviceRestClient.apply(request, DocumentInfoResponse.class);

                final PostImage image = PostImageMapper.INSTANCE.convert(response.getDocument());
                postImageList.add(image);

            }

            final PostResponse response = PostResponse.builder()
                    .id(postEntity.getId())
                    .description(postEntity.getDescription())
                    .imageResponse(postImageList)
                    .build();

            posts.add(response);
        }

        return posts;
    }
}
