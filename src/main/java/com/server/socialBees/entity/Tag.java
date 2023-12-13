package com.server.socialBees.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="tag")
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name="name")
    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonBackReference
    private Set<Work> works = new HashSet<>();

    @ManyToMany(mappedBy = "tags")
    @JsonBackReference
    private Set<User> users = new HashSet<>();
}
