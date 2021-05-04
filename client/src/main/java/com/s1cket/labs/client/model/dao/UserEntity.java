package com.s1cket.labs.client.model.dao;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="user_entity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
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

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<InterlocutorEntity> interlocutors;
}
