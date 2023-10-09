package com.food.recipe.api.service.document;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.DocumentEntity;
import com.food.recipe.api.model.DocumentInfoDTO;
import com.food.recipe.api.model.response.DocumentInfoResponse;
import com.food.recipe.api.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Semih, 3.07.2023
 */
@Service
@RequiredArgsConstructor
public class GetDocumentInfoService implements SimpleTask<Long, DocumentInfoResponse> {

    private final DocumentRepository documentRepository;

    /**
     * Get specific document information executable service
     * @param documentId the function argument
     * @return DocumentInfoResponse
     */
    @Override
    public DocumentInfoResponse apply(Long documentId) {

        final DocumentEntity documentInfo = documentRepository.findByDocumentId(documentId);

        if (Objects.nonNull(documentInfo)) {
            final DocumentInfoDTO documentInfoDTO = DocumentInfoDTO.builder()
                    .userId(documentInfo.getUserId())
                    .documentId(documentInfo.getId())
                    .fileType(documentInfo.getFileType())
                    .fileName(documentInfo.getFileName())
                    .data(documentInfo.getData())
                    .time(documentInfo.getTime())
                    .build();
            return DocumentInfoResponse.builder().document(documentInfoDTO).build();
        }

        return null;
    }
}
