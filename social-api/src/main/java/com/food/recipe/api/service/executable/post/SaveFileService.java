package com.food.recipe.api.service.executable.post;

import com.food.recipe.api.Constant;
import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.model.RestClientRequest;
import com.food.recipe.api.model.document.Base64Files;
import com.food.recipe.api.model.document.request.SaveDocumentBase64Request;
import com.food.recipe.api.model.document.response.SaveDocumentResponse;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Semih, 10.10.2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SaveFileService implements SimpleTask<SaveFileInput, List<SaveDocumentResponse>> {

    private final ServiceRestClient<SaveDocumentResponse> serviceRestClient;

    @Override
    public List<SaveDocumentResponse> apply(SaveFileInput saveFileInput) {
        // Create a new SaveDocumentBase64Request object
        SaveDocumentBase64Request coreDocumentRequest = new SaveDocumentBase64Request();
        // Set the username and userId of the request object
        coreDocumentRequest.setUsername(saveFileInput.getUsername());
        coreDocumentRequest.setUserId(saveFileInput.getUserId());

        // Create a list to hold Base64Files objects
        final List<Base64Files> files = new ArrayList<>();
        // Loop through each MultipartFile in the input's files list
        for (MultipartFile file : saveFileInput.getFiles()) {
            // Create a new Base64Files object
            final Base64Files base64File = new Base64Files();
            // Set the file type and name of the Base64Files object
            base64File.setFileType(file.getContentType());
            base64File.setFileName(file.getOriginalFilename());
            try {
                // Convert the MultipartFile to a base64 string and set it in the Base64Files object
                base64File.setBase64Data(MultipartFileToBase64Utils.convert(file));
            } catch (IOException e) {
                // Throw a RuntimeException if there is an IOException
                throw new RuntimeException(e);
            }
            // Add the Base64Files object to the list of files
            files.add(base64File);
        }
        // Set the files list of the request object
        coreDocumentRequest.setFilesList(files);

        // Create a new RestClientRequest object
        final RestClientRequest<?> restClientRequest = RestClientRequest.builder()
                .url("http://localhost:9545".concat("/api/doc/v1/save-base64"))
                .requestMethod(HttpMethod.POST)
                .body(coreDocumentRequest)
                .build();
        // Send the request and get a response as a List of SaveDocumentResponse objects
        List<SaveDocumentResponse> response = (List<SaveDocumentResponse>) serviceRestClient.apply(restClientRequest, SaveDocumentResponse.class);
        // Return the response if it is not null, otherwise return an empty list
        return Objects.nonNull(response) ? response : Collections.emptyList();
    }
}
