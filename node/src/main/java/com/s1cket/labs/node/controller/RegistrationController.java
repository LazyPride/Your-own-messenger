package com.s1cket.labs.node.controller;

import com.s1cket.labs.node.model.UserDto;
import com.s1cket.labs.node.service.UserService;
import com.s1cket.labs.node.service.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
@AllArgsConstructor
public class RegistrationController {

    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {

        try {
            UserDto userResponse = userService.registerUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
        } catch (ValidationException e) {
            log.info("Can't register user. " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}
