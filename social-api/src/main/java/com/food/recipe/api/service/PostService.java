package com.food.recipe.api.service;

import com.food.recipe.api.model.document.Base64Files;
import com.food.recipe.api.model.request.BaseRequest;
import com.food.recipe.api.model.request.post.GetUserPostRequest;
import com.food.recipe.api.model.request.post.PostRequest;
import com.food.recipe.api.model.response.post.CreatePostResponse;
import com.food.recipe.api.model.response.post.UserPostResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Semih, 1.10.2023
 */
@Service
public interface PostService extends LikeDislikeService {

    CreatePostResponse createPost(PostRequest request);

    CreatePostResponse createPostViaFile(MultipartFile[] files, String username, Long id);

    List<Base64Files> convertMultipartBase64(MultipartFile[] files);

    UserPostResponse userPosts(GetUserPostRequest request);
}
