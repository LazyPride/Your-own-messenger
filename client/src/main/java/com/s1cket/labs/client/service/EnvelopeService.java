package com.s1cket.labs.client.service;

import com.s1cket.labs.client.model.dao.EnvelopeEntity;
import com.s1cket.labs.client.model.dto.*;
import com.s1cket.labs.client.model.mapper.CycleAvoidingMappingContext;
import com.s1cket.labs.client.model.mapper.EnvelopeMapper;
import com.s1cket.labs.client.model.repository.EnvelopeRepository;
import com.s1cket.labs.client.service.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

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

    public EnvelopeDto save(EnvelopeWebDto envelopeWebDto, UserDto userDto) throws ValidationException {

        InterlocutorDto interlocutor = userDto.getInterlocutors().stream()
                .filter(i -> i.getAddress().equals(envelopeWebDto.getAddressFrom()))
                .findFirst()
                .orElseThrow(() -> new ValidationException("Interlocutor wasn't found."));

        LetterDto letter = LetterDto.builder()
                .createTime(OffsetDateTime.now())
                .text(envelopeWebDto.getLetter().getText())
                .build();

        EnvelopeDto envelope = EnvelopeDto.builder()
                .addressFrom(envelopeWebDto.getAddressFrom())
                .addressTo(envelopeWebDto.getAddressTo())
                .letter(letter)
                .interlocutor(interlocutor)
                .build();

        letter.setEnvelope(envelope);

        EnvelopeEntity entity = mapper.envelopeDtoToEntity(envelope, new CycleAvoidingMappingContext());

        EnvelopeDto savedDto = mapper.envelopeEntityToDto(repository.save(entity),
                new CycleAvoidingMappingContext());

        return savedDto;
    }

}
