package com.s1cket.labs.client.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterlocutorDto {
    private Long id;

    private String nickname;

    private String address;

    private String publicKey;

    private UserDto user;

    private Set<EnvelopeDto> envelopes;

    private Set<ChatRoomDto> rooms;
}
