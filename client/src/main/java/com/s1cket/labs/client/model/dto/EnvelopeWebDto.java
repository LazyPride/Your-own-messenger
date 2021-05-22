package com.s1cket.labs.client.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnvelopeWebDto implements Serializable {
    private String addressFrom;

    private String addressTo;

    private LetterWebDto letter;
}
