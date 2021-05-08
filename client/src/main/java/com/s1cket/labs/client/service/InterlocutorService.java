package com.s1cket.labs.client.service;

import com.s1cket.labs.client.model.dao.InterlocutorEntity;
import com.s1cket.labs.client.model.dto.InterlocutorDto;
import com.s1cket.labs.client.model.mapper.CycleAvoidingMappingContext;
import com.s1cket.labs.client.model.mapper.InterlocutorMapper;
import com.s1cket.labs.client.model.mapper.UserMapper;
import com.s1cket.labs.client.model.repository.InterlocutorRepository;
import com.s1cket.labs.client.model.repository.UserRepository;
import com.s1cket.labs.client.model.validator.InterlocutorValidator;
import com.s1cket.labs.client.service.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InterlocutorService {

    private final InterlocutorRepository repository;

    private final InterlocutorMapper mapper;

    private final InterlocutorValidator validator;

    public InterlocutorDto save(InterlocutorDto interlocutorDto) throws ValidationException {

        validator.validate(interlocutorDto);

        var mappedEntity = mapper.interlocutorDtoToEntity(interlocutorDto,
                new CycleAvoidingMappingContext());

        var savedEntity = repository.save(mappedEntity);
        var savedDto = mapper.interlocutorEntityToDto(savedEntity,
                new CycleAvoidingMappingContext());

        return savedDto;
    }
}
