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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="work")
public class Work {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20, name="title")
    private String title;

    @Column(nullable = false, length = 180, name="content")
    private String content;

    @Column(nullable = false, name="date")
    private LocalDate date;

    @Column(nullable = false, name="isDeleted")
    private boolean isDeleted;

    @OneToOne(mappedBy = "work")
    @JsonManagedReference
    private FileDB fileDB;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @ManyToMany
    @JoinTable(
            name="work_tag",
          joinColumns = @JoinColumn(name="work_id"),
    inverseJoinColumns = @JoinColumn(name="tag_id"))
    @JsonManagedReference
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "work", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JsonManagedReference
    private Set<Comment> comments = new HashSet<>();
}
