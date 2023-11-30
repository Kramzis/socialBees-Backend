package com.server.socialBees.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private Long id;

    @Column(nullable = false, length = 8, unique = true, name="username")
    private String username;

    @Column(nullable = false, length = 50, unique = true, name="email")
    private String email;

    @Column(nullable = false, length = 64, name="password")
    private String password;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = false, name="surname")
    private String surname;

    @Column(name="birthday")
    private LocalDate birthday;

    @Column(nullable = false, name="isDeleted")
    private boolean isDeleted;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Work> works = new HashSet<>();

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}
