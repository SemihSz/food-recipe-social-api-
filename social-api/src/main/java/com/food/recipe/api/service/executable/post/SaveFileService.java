package com.food.recipe.api.service.executable.post;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.model.RestClientRequest;
import com.food.recipe.api.model.document.Base64Files;
import com.food.recipe.api.model.document.request.SaveDocumentBase64Request;
import com.food.recipe.api.model.document.response.SaveDocumentResponse;
import com.food.recipe.api.model.input.SaveFileInput;
import com.food.recipe.api.service.client.ServiceRestClient;
import com.food.recipe.api.service.executable.converter.ConvertBase64Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Semih, 10.10.2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SaveFileService implements SimpleTask<SaveFileInput, List<SaveDocumentResponse>> {

    private final ServiceRestClient<SaveDocumentResponse[]> serviceRestClient;

    private final ConvertBase64Service convertBase64Service;

    @Override
    public List<SaveDocumentResponse> apply(SaveFileInput saveFileInput) {
        // Create a new SaveDocumentBase64Request object
        SaveDocumentBase64Request coreDocumentRequest = new SaveDocumentBase64Request();
        // Set the username and userId of the request object
        coreDocumentRequest.setUsername(saveFileInput.getUsername());
        coreDocumentRequest.setUserId(saveFileInput.getUserId());

        // Create a list to hold Base64Files objects
        List<Base64Files> files = new ArrayList<>();

        // controlling multipart files with ? operator
        files = Objects.nonNull(saveFileInput.getFiles()) ? convertBase64Service.apply(saveFileInput.getFiles()) : saveFileInput.getBase64StringList();

        // Set the files list of the request object
        coreDocumentRequest.setFilesList(files);

        // Create a new RestClientRequest object
        final RestClientRequest<?> restClientRequest = RestClientRequest.builder()
                .url("http://localhost:9545".concat("/api/doc/v1/save-base64"))
                .requestMethod(HttpMethod.POST)
                .body(coreDocumentRequest)
                .build();
        // Send the request and get a response as a List of SaveDocumentResponse objects
        SaveDocumentResponse[] response =  serviceRestClient.apply(restClientRequest, SaveDocumentResponse[].class);
        // Return the response if it is not null, otherwise return an empty list
        return Objects.nonNull(response) ? Arrays.stream(response).toList(): Collections.emptyList();
    }
}
