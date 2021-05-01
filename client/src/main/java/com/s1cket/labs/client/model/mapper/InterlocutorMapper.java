package com.s1cket.labs.client.model.mapper;

import com.s1cket.labs.client.model.dao.InterlocutorEntity;
import com.s1cket.labs.client.model.dto.InterlocutorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterlocutorMapper {
    
    InterlocutorEntity interlocutorDtoToEntity(InterlocutorDto interlocutorDto);

    InterlocutorDto interlocutorEntityToDto(InterlocutorEntity interlocutorEntity);

}
