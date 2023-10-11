package com.food.recipe.api.service;

import com.food.recipe.api.model.document.response.SaveDocumentResponse;
import com.food.recipe.api.model.request.post.CommentRequest;
import com.food.recipe.api.model.request.post.PostRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Semih, 1.10.2023
 */
@Service
public interface PostService {

    Boolean createPost(PostRequest request);

    List<SaveDocumentResponse> createPostViaFile(MultipartFile[] files, String username, Long id);

    Boolean addComment(CommentRequest request);
}
