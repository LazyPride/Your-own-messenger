package com.s1cket.labs.client.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;

    private String login;

    @ToString.Exclude
    private String password;

    @ToString.Exclude
    private String privateKey;

    private String publicKey;

    private String address;

    @EqualsAndHashCode.Exclude
    private Set<InterlocutorDto> interlocutors;
}
