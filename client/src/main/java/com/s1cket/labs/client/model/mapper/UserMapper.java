package com.s1cket.labs.client.model.mapper;

import com.s1cket.labs.client.model.dao.UserEntity;
import com.s1cket.labs.client.model.dto.UserDto;
import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = InterlocutorMapper.class)
public interface UserMapper {

    UserEntity userDtoToEntity(UserDto userDto, @Context CycleAvoidingMappingContext context);

    UserDto userEntityToDto(UserEntity userEntity, @Context CycleAvoidingMappingContext context);

}
