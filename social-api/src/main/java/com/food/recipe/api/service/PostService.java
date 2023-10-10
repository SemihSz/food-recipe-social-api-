package com.food.recipe.api.service;

import com.food.recipe.api.model.request.post.PostRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Semih, 1.10.2023
 */
@Service
public interface PostService {

    Boolean createPost(PostRequest request);

    Boolean createPost(MultipartFile[] files, String username, Long id);
}
