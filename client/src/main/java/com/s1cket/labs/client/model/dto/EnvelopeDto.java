package com.s1cket.labs.client.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvelopeDto {
    private Long id;

    private String addressFrom;

    private String addressTo;

    private LetterDto letter;

    private InterlocutorDto interlocutor;
}
