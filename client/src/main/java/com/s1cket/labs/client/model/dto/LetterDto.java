package com.s1cket.labs.client.model.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LetterDto implements Comparable<LetterDto> {
    private Long id;

    private OffsetDateTime createTime;

    private byte[] text;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EnvelopeDto envelope;

    @Override
    public int compareTo(LetterDto o) {
        return this.createTime.compareTo(o.getCreateTime());
    }
}
