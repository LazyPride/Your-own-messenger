package com.s1cket.labs.client.model.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="envelope")
@Data
public class EnvelopeEntity implements Comparable<EnvelopeEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressFrom;

    private String addressTo;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "envelope")
    @PrimaryKeyJoinColumn
    private LetterEntity letter;

    @ManyToOne
    @JoinColumn(name="interlocutor_id", nullable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private InterlocutorEntity interlocutor;

    @Override
    public int compareTo(EnvelopeEntity o) {
        return this.letter.compareTo(o.getLetter());
    }
}
