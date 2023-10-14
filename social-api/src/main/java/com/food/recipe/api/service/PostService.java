package com.food.recipe.api.service;

import com.food.recipe.api.model.document.Base64Files;
import com.food.recipe.api.model.request.post.CommentRequest;
import com.food.recipe.api.model.request.post.PostRequest;
import com.food.recipe.api.model.response.CreatePostResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Semih, 1.10.2023
 */
@Service
public interface PostService {

    CreatePostResponse createPost(PostRequest request);

    CreatePostResponse createPostViaFile(MultipartFile[] files, String username, Long id);

    Boolean addComment(CommentRequest request);

    List<Base64Files> convertMultipartBase64(MultipartFile[] files);
}
