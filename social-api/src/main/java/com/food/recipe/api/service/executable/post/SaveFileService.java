package com.food.recipe.api.service.executable.post;

import com.food.recipe.api.Constant;
import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.model.RestClientRequest;
import com.food.recipe.api.model.document.Base64Files;
import com.food.recipe.api.model.document.request.SaveDocumentBase64Request;
import com.food.recipe.api.model.input.SaveFileInput;
import com.food.recipe.api.service.client.ServiceRestClient;
import com.food.recipe.api.util.MultipartFileToBase64Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MultipartBody;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Semih, 10.10.2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SaveFileService implements SimpleTask<SaveFileInput, Boolean> {

    private final ServiceRestClient<Object> serviceRestClient;

    @Override
    public Boolean apply(SaveFileInput saveFileInput) {

        SaveDocumentBase64Request coreDocumentRequest = new SaveDocumentBase64Request();
        coreDocumentRequest.setUsername(saveFileInput.getUsername());
        coreDocumentRequest.setUserId(saveFileInput.getUserId());
        final List<Base64Files> files = new ArrayList<>();
        for (MultipartFile file : saveFileInput.getFiles()) {
            final Base64Files base64File = new Base64Files();
            base64File.setFileType(file.getContentType());
            base64File.setFileName(file.getOriginalFilename());
            try {
                base64File.setBase64Data(MultipartFileToBase64Utils.convert(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            files.add(base64File);
        }
        coreDocumentRequest.setFilesList(files);

        // TODO  Required part 'file' is not present.]

        final RestClientRequest restClientRequest = RestClientRequest.builder()
                .url("http://localhost:9545".concat("/api/doc/v1/save-base64"))
                .requestMethod(HttpMethod.POST)
                .body(coreDocumentRequest)
                .build();

        return Objects.nonNull(serviceRestClient.apply(restClientRequest, Object.class));
    }
}
