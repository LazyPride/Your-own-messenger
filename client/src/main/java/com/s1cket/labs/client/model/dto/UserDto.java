package com.s1cket.labs.client.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;

    private String login;

    private String password;

    private String privateKey;

    private String publicKey;

    private String address;

    private Set<ChatRoomDto> rooms;

    private Set<InterlocutorDto> interlocutors;
}
