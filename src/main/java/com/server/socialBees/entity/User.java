package com.server.socialBees.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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

    @Column(nullable = false, length = 50, unique = true, name="email")
    private String email;

    @Column(nullable = false, length = 64, name="password")
    private String password;

    @Column(nullable = false, name="isDeleted")
    private boolean isDeleted;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private UserInfo userInfo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AccountData accountData;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    //@PrimaryKeyJoinColumn
    @JsonManagedReference
    private Set<Work> works = new HashSet<>();

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Comment> comments;

//    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
//    private Set<Tag> tags;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<Funding> funding;
}
