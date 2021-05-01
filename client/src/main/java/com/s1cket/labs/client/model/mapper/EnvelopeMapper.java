package com.s1cket.labs.client.model.mapper;

import com.s1cket.labs.client.model.dao.EnvelopeEntity;
import com.s1cket.labs.client.model.dto.EnvelopeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnvelopeMapper {
    
    EnvelopeEntity envelopeDtoToEntity(EnvelopeDto envelopeDto);

    EnvelopeDto envelopeEntityToDto(EnvelopeEntity envelopeEntity);

}
