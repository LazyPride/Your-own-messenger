package com.s1cket.labs.node.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private String login;

    @ToString.Exclude
    private String password;

    private String address;
}
