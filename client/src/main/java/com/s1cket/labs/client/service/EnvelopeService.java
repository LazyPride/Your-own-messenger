package com.s1cket.labs.client.service;

import com.s1cket.labs.client.model.dto.EnvelopeDto;
import com.s1cket.labs.client.model.dto.InterlocutorDto;
import com.s1cket.labs.client.model.mapper.CycleAvoidingMappingContext;
import com.s1cket.labs.client.model.mapper.EnvelopeMapper;
import com.s1cket.labs.client.model.repository.EnvelopeRepository;
import com.s1cket.labs.client.service.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnvelopeService {

    private final EnvelopeMapper mapper;

    private final EnvelopeRepository repository;


    public EnvelopeDto save(EnvelopeDto envelopeDto) {

        //validator.validate(interlocutorDto);

        var mappedEntity = mapper.envelopeDtoToEntity(envelopeDto,
                new CycleAvoidingMappingContext());

        var savedEntity = repository.save(mappedEntity);
        var savedDto = mapper.envelopeEntityToDto(savedEntity,
                new CycleAvoidingMappingContext());

        return savedDto;
    }
}
