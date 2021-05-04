package com.s1cket.labs.client.model.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LetterDto {
    private Long id;

    private OffsetDateTime createTime;

    private String text;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EnvelopeDto envelope;
}
