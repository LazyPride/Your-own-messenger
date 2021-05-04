package com.s1cket.labs.client.model.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterlocutorDto {
    private Long id;

    private String nickname;

    private String address;

    private String publicKey;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserDto user;

    @EqualsAndHashCode.Exclude
    private Set<EnvelopeDto> envelopes;
}
