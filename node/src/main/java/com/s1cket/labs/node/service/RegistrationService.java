package com.s1cket.labs.node.service;

import com.s1cket.labs.node.model.AppUser;
import com.s1cket.labs.node.model.AppUserRole;
import com.s1cket.labs.node.model.RegistrationRequest;
import com.s1cket.labs.node.service.AppUserService;
import com.s1cket.labs.node.service.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        // TODO: Validate login

        String token = appUserService.signUpUser(
                new AppUser(
                        request.getLogin(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );

        return token;
    }
}
