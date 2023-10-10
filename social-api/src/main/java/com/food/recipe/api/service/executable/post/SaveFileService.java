package com.food.recipe.api.service.executable.post;

import com.food.recipe.api.Constant;
import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.model.RestClientRequest;
import com.food.recipe.api.model.input.SaveFileInput;
import com.food.recipe.api.service.client.ServiceRestClient;
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
import java.util.Arrays;
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

        // Form verilerini içeren MultiValueMap
        MultiValueMap<String, Object> formFields = new LinkedMultiValueMap<>();
        MultiValueMap<String, Object> fileParts = new LinkedMultiValueMap<>();;

        formFields.add("username", saveFileInput.getUsername());
        formFields.add("id", saveFileInput.getUserId());

        if (saveFileInput.getFiles().length > 0) {

            for (MultipartFile file : saveFileInput.getFiles()) {
                try {
                    fileParts.add("file", new ByteArrayResource(file.getBytes()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // Form verileri ve dosyaları birleştirin
        MultiValueMap<String, Object> formBody = new LinkedMultiValueMap<>();
        formBody.putAll(formFields);
        formBody.putAll(fileParts);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        // TODO  Required part 'file' is not present.]
//        MultipartBody body = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("username", saveFileInput.getUsername())
//                .addFormDataPart("id", saveFileInput.getUserId().toString())
//                .addFormDataPart("file", Arrays.stream(saveFileInput.getFiles()).findFirst().get().getBytes())
//                .build();

        final RestClientRequest restClientRequest = RestClientRequest.builder()
                .url("http://localhost:9545".concat("/api/doc/v1/save-multipart"))
                .requestMethod(HttpMethod.POST)
                .formBody(formBody)
                .httpHeaders(headers)
                .build();

        return Objects.nonNull(serviceRestClient.apply(restClientRequest, Object.class));
    }
}
