package com.s1cket.labs.client.model.validator;

import com.s1cket.labs.client.service.exception.ValidationException;

public interface IValidator<D> {
    void validate(D dto) throws ValidationException;
}
