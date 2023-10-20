package com.food.recipe.api;

import org.springframework.stereotype.Component;

/**
 * Created by Semih, 7.10.2023
 */
@Component
public class Constant {

    public static class URL {

        public static final String AUTH_INFO = "/summary/info/{username}";

        public static class Logger {

            public static final String LOGGER_SAVE = "/logger/save";
            public Logger() {
            }
        }

        public static class Document {

            public static final String SAVE_FILE_BASE_64 = "/api/doc/v1/save-base64";
            public Document() {
            }
        }

    }

    public static class Exception {

        public static final String USER_EXIST = "user_exits";

        public Exception() {
        }
    }
}
