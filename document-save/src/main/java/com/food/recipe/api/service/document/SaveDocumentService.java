package com.food.recipe.api.service.document;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.DocumentEntity;
import com.food.recipe.api.model.document.request.SaveDocumentRequest;
import com.food.recipe.api.model.document.response.SaveDocumentResponse;
import com.food.recipe.api.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Semih, 3.07.2023
 */
@Service
@RequiredArgsConstructor
public class SaveDocumentService implements SimpleTask<SaveDocumentRequest, SaveDocumentResponse> {

    private final MultipartFileControlService fileControlService;

    private final DocumentRepository documentRepository;

    /**
     * Save document with Multi part file request body
     * @param request the function argument
     * @return
     */
    @Override
    public SaveDocumentResponse apply(SaveDocumentRequest request) {

        final String fileName = StringUtils.cleanPath(Objects.requireNonNull(request.getFile().getOriginalFilename()));

        /**
         * Control file size and allowed extensions
         */
        try {
            fileControlService.accept(request.getFile());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            final DocumentEntity document = DocumentEntity.builder()
                    .userId(request.getUserId())
                    .username(request.getUsername())
                    .fileType(request.getFile().getContentType())
                    .fileName(fileName)
                    .data(request.getFile().getBytes())
                    .time(LocalDateTime.now())
                    .build();
            documentRepository.save(document);
            return SaveDocumentResponse.builder().documentId(document.getId())
                    .userId(document.getUserId()).fileName(document.getFileName()).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
