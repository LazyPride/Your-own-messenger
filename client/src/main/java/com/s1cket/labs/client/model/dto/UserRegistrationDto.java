package com.s1cket.labs.client.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto implements Serializable {
    private String login;

    @ToString.Exclude
    private String password;

    private String address;
}