package com.food.recipe.api.service.document;

import com.food.recipe.api.model.request.document.SaveDocumentBase64Request;
import com.food.recipe.api.model.request.document.SaveDocumentRequest;
import com.food.recipe.api.model.document_response.DocumentInfoResponse;
import com.food.recipe.api.model.document_response.DocumentListResponse;
import com.food.recipe.api.model.document_response.SaveDocumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final SaveDocumentService saveDocumentService;

    private final SaveBase64DocumentService saveBase64DocumentService;

    private final GetDocumentListService getDocumentListService;

    private final GetDocumentInfoService getDocumentInfoService;

    private final  SaveMultipartDocumentService saveMultipartDocumentService;

    @Override
    public SaveDocumentResponse save(SaveDocumentRequest request) {
        return saveDocumentService.apply(request);
    }

    @Override
    public List<SaveDocumentResponse> saveBase64(SaveDocumentBase64Request request) {
        return saveBase64DocumentService.apply(request);
    }

    @Override
    public DocumentListResponse documents(String username) {
        return getDocumentListService.apply(username);
    }

    @Override
    public DocumentInfoResponse documentInfo(Long documentId) {
        return getDocumentInfoService.apply(documentId);
    }

    @Override
    public List<SaveDocumentResponse> saveMultipleFile(SaveDocumentRequest request) {
        return saveMultipartDocumentService.apply(request);
    }
}
