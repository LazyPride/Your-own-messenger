package com.s1cket.labs.client.service;

import com.s1cket.labs.client.model.dao.UserEntity;
import com.s1cket.labs.client.model.dto.UserDto;
import com.s1cket.labs.client.model.mapper.CycleAvoidingMappingContext;
import com.s1cket.labs.client.model.mapper.UserMapper;
import com.s1cket.labs.client.model.repository.UserRepository;
import com.s1cket.labs.client.service.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto findByLogin(String login) throws ServiceException {
        UserEntity userEntity;

        try {
            userEntity = userRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
        } catch (Exception ex) {
            throw new ServiceException("User " + login + " is not found.", ex);
        }
        return userMapper.userEntityToDto(userEntity, new CycleAvoidingMappingContext());
    }
}
