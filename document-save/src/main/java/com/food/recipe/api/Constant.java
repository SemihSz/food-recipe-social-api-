package com.food.recipe.api;

import org.springframework.stereotype.Component;

/**
 * Created by Semih, 7.10.2023
 */
@Component
public class Constant {

    public static class URL {

        public static final String AUTH_INFO = "/api/doc/v1/save-multipart";

    }

    public static class Exception {

        public static final String USER_EXIST = "user_exits";

        public Exception() {
        }
    }
}
