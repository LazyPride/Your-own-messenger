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
public class LetterEntity implements Comparable<LetterEntity> {
    @Id
    @Column(name = "envelope_id")
    private Long id;

    @CreationTimestamp
    private OffsetDateTime createTime;

    private String text;

    @OneToOne
    @MapsId
    @JoinColumn(name = "envelope_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private EnvelopeEntity envelope;

    @Override
    public int compareTo(LetterEntity o) {
        return this.createTime.compareTo(o.getCreateTime());
    }
}
