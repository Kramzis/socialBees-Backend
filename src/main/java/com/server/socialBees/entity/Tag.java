package com.server.socialBees.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tag")
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name="name")
    private String name;

    @Column(nullable = false, name="isDeleted")
    private boolean isDeleted;

    @JsonBackReference
    @ManyToMany(mappedBy = "tags")
    //@JoinColumn(name="work_id", nullable=false)
    private Set<Work> works = new HashSet<>();

    @ManyToMany(mappedBy = "tags")
    @JsonBackReference
    private Set<User> users = new HashSet<>();
}
