package com.s1cket.labs.client.model.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name="letter")
@Data
public class LetterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private OffsetDateTime createTime;

    private String text;

    @OneToOne(mappedBy = "letter")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private EnvelopeEntity envelope;
}
