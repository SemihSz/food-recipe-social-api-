package com.food.recipe.api.constant;

import org.springframework.stereotype.Component;

/**
 * Created by Semih, 30.09.2023
 */
@Component
public class AuthenticationConstant {

    public AuthenticationConstant() {
    }

    public static class Exception {

        public static final String AUTH_USER_EXIST = "authentication.user_exits";

        public static final String AUTH_USER_PASSWORD_NOT_MATCH = "authentication.passwords.not_match";

        public static final String AUTH_EMAIL_EXIST = "authentication.email_exits";

        public Exception() {
        }
    }
}
