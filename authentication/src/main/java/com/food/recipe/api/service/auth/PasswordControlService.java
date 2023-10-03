package com.food.recipe.api.service.auth;

import com.food.recipe.api.Mappers;
import com.food.recipe.api.constant.AuthenticationConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by Semih, 1.10.2023
 */
@Service
@RequiredArgsConstructor
public class PasswordControlService implements Mappers<String, String, Boolean> {

    private final MessageSource messageSource;

    @Override
    public Boolean apply(String pass1, String pass2) {
        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
        final String pass1Encode = bcryptEncoder.encode(pass1);
        final String pass2Encode = bcryptEncoder.encode(pass2);

        if (bcryptEncoder.matches(pass1, pass1Encode) && bcryptEncoder.matches(pass2, pass2Encode)) {
            return Boolean.TRUE;
        }

        throw new com.food.recipe.api.exception.AuthException((messageSource.getMessage(AuthenticationConstant.Exception.AUTH_USER_PASSWORD_NOT_MATCH,
                null, Locale.ENGLISH)));
    }
}
