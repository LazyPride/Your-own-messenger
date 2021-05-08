package com.s1cket.labs.client.model.validator;

import com.s1cket.labs.client.model.dto.InterlocutorDto;
import com.s1cket.labs.client.service.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class InterlocutorValidator implements IValidator<InterlocutorDto> {
    @Override
    public void validate(InterlocutorDto dto) throws ValidationException {
        if (dto.getNickname() == null || dto.getNickname().isEmpty()) {
            throw new ValidationException("Nickname field can't be empty!");
        }
        if (dto.getAddress() == null || dto.getAddress().isEmpty()) {
            throw new ValidationException("Address field can't be empty!");
        }
        if (dto.getPublicKey() == null || dto.getPublicKey().isEmpty()) {
            throw new ValidationException("PublicKey field can't be empty!");
        }
    }
}
