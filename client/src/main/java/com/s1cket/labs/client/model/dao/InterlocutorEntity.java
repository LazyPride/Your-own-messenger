package com.s1cket.labs.client.model.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.SortedSet;

@Entity
@Table(name="interlocutor")
@Data
public class InterlocutorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String address;

    private String publicKey;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity user;

    @OneToMany(mappedBy="interlocutor", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @SortNatural
    private SortedSet<EnvelopeEntity> envelopes;
}
