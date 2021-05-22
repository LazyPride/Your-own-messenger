package com.s1cket.labs.node.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnvelopeDto implements Serializable {
    private String addressFrom;

    private String addressTo;

    private LetterDto letter;
}
