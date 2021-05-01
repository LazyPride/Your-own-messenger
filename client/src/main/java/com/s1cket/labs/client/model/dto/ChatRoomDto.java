package com.s1cket.labs.client.model.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {
    private Long id;

    private String title;

    private String address;

    private UserDto user;

    private Set<EnvelopeDto> envelopes;

    private Set<InterlocutorDto> participants;
}
