package com.s1cket.labs.client.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LetterDto {
    private Long id;

    private OffsetDateTime createTime;

    private String text;

    private EnvelopeDto envelope;
}
