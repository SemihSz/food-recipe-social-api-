package com.food.recipe.api.exception;

/**
 * Created by Semih, 15.10.2023
 */
public class SocialApiAuthException extends RuntimeException {

    public SocialApiAuthException(Exception pCause) {
        super(pCause);
    }

    public SocialApiAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public SocialApiAuthException(String message) {
        super(message);
    }
}
