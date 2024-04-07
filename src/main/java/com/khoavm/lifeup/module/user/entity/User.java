package com.khoavm.lifeup.module.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "\"user\"", schema = "life_up")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "user")
    private Set<UserCredential> userCredentials = new LinkedHashSet<>();

}