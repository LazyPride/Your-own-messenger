package com.s1cket.labs.client.model.mapper;

import com.s1cket.labs.client.model.dao.UserEntity;
import com.s1cket.labs.client.model.dto.UserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity userDtoToEntity(UserDto userDto);

    UserDto userEntityToDto(UserEntity userEntity);

}
