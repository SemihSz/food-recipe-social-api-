package com.food.recipe.api.service.executable.converter;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.model.document.Base64Files;
import com.food.recipe.api.util.MultipartFileToBase64Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semih, 14.10.2023
 */
@Service
@Slf4j
public class ConvertBase64Service implements SimpleTask<MultipartFile[], List<Base64Files>> {

    /**
     * Convert multipart files to ${@link List<Base64Files>}
     * @param files the function argument
     * @return
     */
    @Override
    public List<Base64Files> apply(MultipartFile[] files) {
        final List<Base64Files> listFiles = new ArrayList<>();
        for (MultipartFile file : files) {
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
            listFiles.add(base64File);
        }
        return listFiles;
    }
}
