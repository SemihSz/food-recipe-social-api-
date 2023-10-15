package com.food.recipe.api;

import org.springframework.stereotype.Component;

/**
 * Created by Semih, 3.07.2023
 */
@Component
public class DocumentConstant {

    public static class Exception {

        public static final String FILE_NOT_EMPTY = "file.not_empty";

        public static final String FILE_NOT_ALLOWED = "file.not_allowed";

        public static final String FILE_SIZE_LIMIT = "file_size_limit";

        public Exception() {
        }
    }

    public DocumentConstant() {
    }
}
