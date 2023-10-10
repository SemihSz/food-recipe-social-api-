package com.food.recipe.api.service.document;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.DocumentEntity;
import com.food.recipe.api.exception.BusinessException;
import com.food.recipe.api.model.document.request.SaveDocumentRequest;
import com.food.recipe.api.model.document.response.SaveDocumentResponse;
import com.food.recipe.api.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveMultipartDocumentService implements SimpleTask<SaveDocumentRequest, List<SaveDocumentResponse>> {

    private final DocumentRepository documentRepository;

    private final MultipartFileControlService fileControlService;


    private static final DataSize MAX_FILE_SIZE = DataSize.ofMegabytes(5);
    
    @Override
    public List<SaveDocumentResponse> apply(SaveDocumentRequest request) {

        List<SaveDocumentResponse> documentResponseList = new ArrayList<>();

        var virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
        
        for (MultipartFile multipartFile: request.getFiles()) {
            virtualThreadExecutor.execute(() -> {
                final String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

                /**
                 * Control file size and allowed extensions
                 */
                try {
                    fileControlService.accept(multipartFile);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }

                try {
                    final DocumentEntity document = DocumentEntity.builder()
                            .userId(request.getUserId())
                            .username(request.getUsername())
                            .fileType(multipartFile.getContentType())
                            .fileName(fileName)
                            .data(multipartFile.getBytes())
                            .time(LocalDateTime.now())
                            .build();
                    documentRepository.save(document);
                    documentResponseList.add(SaveDocumentResponse.builder().documentId(document.getId()).userId(document.getUserId()).fileName(document.getFileName()).build());

                } catch (IOException e) {
                    throw new BusinessException( "İşlemlerinizi şu vakit gerçekleştiremiyoruz.");
                }
            });
        }

        // Java Virtual Threads Executor'ünü kapatın (Java 17+ kullanılıyorsa bu gerekli değil)
        if (virtualThreadExecutor instanceof AutoCloseable) {
            try {
                ((AutoCloseable) virtualThreadExecutor).close();
            } catch (Exception e) {
                throw new BusinessException( "İşlemlerinizi şu vakit gerçekleştiremiyoruz.");
            }
        }
        return documentResponseList;
    }
}
