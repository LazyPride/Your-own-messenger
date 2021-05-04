package com.s1cket.labs.client.model.mapper;

import com.s1cket.labs.client.model.dao.InterlocutorEntity;
import com.s1cket.labs.client.model.dto.InterlocutorDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EnvelopeMapper.class)
public interface InterlocutorMapper {

    InterlocutorEntity interlocutorDtoToEntity(InterlocutorDto interlocutorDto, @Context CycleAvoidingMappingContext context);

    InterlocutorDto interlocutorEntityToDto(InterlocutorEntity interlocutorEntity, @Context CycleAvoidingMappingContext context);
}
