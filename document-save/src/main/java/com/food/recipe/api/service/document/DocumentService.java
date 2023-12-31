package com.food.recipe.api.service.document;

import com.food.recipe.api.model.document.request.SaveDocumentBase64Request;
import com.food.recipe.api.model.document.request.SaveDocumentRequest;
import com.food.recipe.api.model.document.response.DocumentInfoResponse;
import com.food.recipe.api.model.document.response.DocumentListResponse;
import com.food.recipe.api.model.document.response.SaveDocumentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocumentService {

    /**
     * Save user document information via, multipart file format
     * @param request
     * @return
     */
    SaveDocumentResponse save(SaveDocumentRequest request);


    /**
     * Save user multiple documents information via, multipart files format
     * @param request
     * @return
     */
    List<SaveDocumentResponse> saveMultipleFile(SaveDocumentRequest request);

    /**
     * Save document information via. base64 format
     * @param request
     * @return
     */
    List<SaveDocumentResponse> saveBase64(SaveDocumentBase64Request request);

    /**
     * User documents information response
     * @param username
     * @return
     */
    DocumentListResponse documents(String username);

    /**
     * Specific selected document info
     * @param documentId
     * @return
     */
    DocumentInfoResponse documentInfo(Long documentId);
}
