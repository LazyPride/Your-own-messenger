package com.s1cket.labs.node.service;

import com.s1cket.labs.node.model.*;
import com.s1cket.labs.node.service.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserValidator userValidator;

    public UserDto registerUser(UserDto userDto) throws ValidationException {
        userValidator.validate(userDto);

        if (userRepository.findByLogin(userDto.getLogin()).orElse(null) != null) {
            throw new ValidationException("User " + userDto.getLogin() + " is already exists!");
        }

        UserEntity userEntity = userMapper.userDtoToEntity(userDto);

        UserEntity savedEntity = userRepository.save(userEntity);

        return userMapper.userEntityToDto(savedEntity);
    }

    public UserDto loginUser(UserLoginDto userDto) throws ValidationException {

        UserEntity entity = userRepository.findByLogin(userDto.getLogin()).orElse(null);
        if (entity == null) {
            throw new ValidationException("User " + userDto.getLogin() + " doesn't exist!");
        }

        if (!userDto.getPassword().equals(entity.getPassword())) {
            throw new ValidationException("Invalid credentials!");
        }

        return userMapper.userEntityToDto(entity);
    }

}
