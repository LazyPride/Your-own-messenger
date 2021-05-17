package com.s1cket.labs.node.model;

import com.s1cket.labs.node.service.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public void validate(UserDto dto) throws ValidationException {
        // TODO: Login and password validation
        if (dto.getLogin() == null || dto.getLogin().isEmpty()) {
            throw new ValidationException("Login field can't be empty!");
        }
        if (dto.getAddress() == null || dto.getAddress().isEmpty()) {
            throw new ValidationException("Address field can't be empty!");
        }
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            throw new ValidationException("Address field can't be empty!");
        }
    }
}
