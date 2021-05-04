package com.s1cket.labs.client.model.mapper;

import com.s1cket.labs.client.model.dao.EnvelopeEntity;
import com.s1cket.labs.client.model.dto.EnvelopeDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = LetterMapper.class)
public interface EnvelopeMapper {
    
    EnvelopeEntity envelopeDtoToEntity(EnvelopeDto envelopeDto, @Context CycleAvoidingMappingContext context);

    EnvelopeDto envelopeEntityToDto(EnvelopeEntity envelopeEntity, @Context CycleAvoidingMappingContext context);

}
