package com.s1cket.labs.client.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnvelopeDto implements Comparable<EnvelopeDto> {
    private Long id;

    private String addressFrom;

    private String addressTo;

    private LetterDto letter;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private InterlocutorDto interlocutor;

    @Override
    public int compareTo(EnvelopeDto o) {
        return this.letter.compareTo(o.getLetter());
    }
}
