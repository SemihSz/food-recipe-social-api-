package com.food.recipe.api.service.auth.change_password;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.UserEntity;
import com.food.recipe.api.model.request.ChangePasswordRequest;
import com.food.recipe.api.repository.UserRepository;
import com.food.recipe.api.service.auth.PasswordControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Semih, 1.10.2023
 */
@Service
@RequiredArgsConstructor
public class ChangePasswordService implements SimpleTask<ChangePasswordRequest, Boolean> {

    private final UserRepository userRepository;

    private final PasswordControlService passwordControlService;

    @Override
    public Boolean apply(ChangePasswordRequest changePasswordRequest) {

        final UserEntity user = userRepository.findByUsernameEntity(changePasswordRequest.getUsername());

        if (Objects.nonNull(user)) {
            // Control old password!
            if (Boolean.TRUE.equals(passwordControlService.apply(user.getPassword(), changePasswordRequest.getOldPassword()))) {

                // Control new and newRe password!
                if (Boolean.TRUE.equals(passwordControlService.apply(changePasswordRequest.getNewPassword(), changePasswordRequest.getNewRePassword()))) {
                    BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
                    user.setPassword(bcryptEncoder.encode(changePasswordRequest.getNewPassword()));
                    userRepository.save(user);
                }
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}
