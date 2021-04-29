package com.s1cket.labs.client.model.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name="envelope")
@Data
public class EnvelopeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressFrom;

    private String addressTo;

    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "letter_id")
    private LetterEntity letter;

    @ManyToOne
    @JoinColumn(name="chat_room_id", nullable=false)
    private ChatRoomEntity chatRoom;

    @ManyToOne
    @JoinColumn(name="interlocutor_id", nullable=false)
    private InterlocutorEntity interlocutor;
}
