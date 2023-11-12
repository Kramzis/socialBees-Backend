package com.server.socialBees.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 8, unique = true, name="username")
    private String username;

    @Column(nullable = false, length = 64, name="password")
    private String password;

    @Column(nullable = false, length = 50, unique = true, name="email")
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserInfo userInfo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AccountData accountData;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Comment> comments;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Work> works;

    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Tag> tags;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Funding> funding;
}
