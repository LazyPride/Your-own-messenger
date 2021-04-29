package com.s1cket.labs.client.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="chat_room")
@Data
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String address;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    @OneToMany(mappedBy="chatRoom")
    private Set<EnvelopeEntity> envelopes;

    @ManyToMany
    private Set<InterlocutorEntity> participants;
}
