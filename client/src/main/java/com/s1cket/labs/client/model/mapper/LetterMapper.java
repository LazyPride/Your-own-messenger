package com.s1cket.labs.client.model.mapper;

import com.s1cket.labs.client.model.dao.LetterEntity;
import com.s1cket.labs.client.model.dto.LetterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LetterMapper {
    
    LetterEntity letterDtoToEntity(LetterDto letterDto);

    LetterDto letterEntityToDto(LetterEntity letterEntity);

}
