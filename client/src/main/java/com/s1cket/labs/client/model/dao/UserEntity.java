package com.s1cket.labs.client.model.dao;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user_entity")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    @ToString.Exclude
    private String password;

    @ToString.Exclude
    private String privateKey;

    private String publicKey;

    private String address;

    @OneToMany(mappedBy="user")
    private Set<ChatRoomEntity> rooms;

    @OneToMany(mappedBy="user")
    private Set<InterlocutorEntity> interlocutors;
}
