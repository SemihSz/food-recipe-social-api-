package com.food.recipe.api.service.auth.register;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.constant.AuthenticationConstant;
import com.food.recipe.api.entity.RoleEntity;
import com.food.recipe.api.entity.UserEntity;
import com.food.recipe.api.enums.RoleTypes;
import com.food.recipe.api.model.request.RegisterRequest;
import com.food.recipe.api.repository.RoleRepository;
import com.food.recipe.api.repository.UserRepository;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


/**
 * Created by Semih, 2.07.2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService implements SimpleTask<RegisterRequest, Boolean> {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final MessageSource messageSource;

    /**
     *  This executable task manage the all register operation. If the username or email has been existed, service will return ${@link AuthException}
     * @param registerRequest the function argument
     * @return
     */
    @Override
    public Boolean apply(RegisterRequest registerRequest) {

        if (Boolean.TRUE.equals(userRepository.existsByUsername(registerRequest.getUsername()))) {
            throw new com.food.recipe.api.exception.AuthException(messageSource.getMessage(AuthenticationConstant.Exception.AUTH_USER_EXIST, null, Locale.ENGLISH));
        } else if (Boolean.TRUE.equals(userRepository.existsByEmail(registerRequest.getEmail()))) {
            throw new com.food.recipe.api.exception.AuthException((messageSource.getMessage(AuthenticationConstant.Exception.AUTH_EMAIL_EXIST, null, Locale.ENGLISH)));
        }

        registerRequest.setPassword(passwordControl(registerRequest.getPassword(), registerRequest.getRePassword()));

        try {
            final RoleTypes strRoles = registerRequest.getRole();
            Set<RoleEntity> roles = new HashSet<>();
            // TODO Upgrade here!
            if (strRoles == null) {
                final RoleEntity userRole = roleRepository.findByName(RoleTypes.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                switch (strRoles) {
                    case ROLE_ADMIN -> {
                        RoleEntity adminRole = roleRepository.findByName(RoleTypes.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case ROLE_MODERATOR -> {
                        RoleEntity modRole = roleRepository.findByName(RoleTypes.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        RoleEntity userRole = roleRepository.findByName(RoleTypes.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            }

            final UserEntity user = UserEntity.builder()
                    .username(registerRequest.getUsername())
                    .name(registerRequest.getName())
                    .surname(registerRequest.getSurname())
                    .email(registerRequest.getEmail())
                    .password(registerRequest.getPassword())
                    .roles(roles)
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();
            userRepository.save(user);

            return Boolean.TRUE;

        } catch (Exception e) {
            log.info(e.getMessage());
            return Boolean.FALSE;
        }
    }

    /**
     * Controlling password and rePassword with encoded version. If it doesnt match code produce ${@link AuthException}
     * @param password String
     * @param rePassword String
     * @return String
     */
    private String passwordControl(String password, String rePassword) {

        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
        final String passwordEncode = bcryptEncoder.encode(password);
        final String rePasswordEncode = bcryptEncoder.encode(rePassword);

        if (bcryptEncoder.matches(password, passwordEncode) && bcryptEncoder.matches(rePassword, rePasswordEncode)) {
            return passwordEncode;
        }

        throw new com.food.recipe.api.exception.AuthException((messageSource.getMessage(AuthenticationConstant.Exception.AUTH_USER_PASSWORD_NOT_MATCH,
                null, Locale.ENGLISH)));
    }
}
