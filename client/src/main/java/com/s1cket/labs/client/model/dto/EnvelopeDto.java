package com.s1cket.labs.client.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvelopeDto {
    private Long id;

    private String addressFrom;

    private String addressTo;

    private LetterDto letter;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private InterlocutorDto interlocutor;
}
