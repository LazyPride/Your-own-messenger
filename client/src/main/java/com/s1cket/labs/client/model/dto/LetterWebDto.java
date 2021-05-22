package com.s1cket.labs.client.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LetterWebDto implements Serializable {
    private String text;
}
