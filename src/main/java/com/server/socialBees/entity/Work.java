package com.server.socialBees.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="work")
public class Work {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 20, name="title")
    private String title;

    @Column(nullable = false, length = 180, name="content")
    private String content;

    @Column(nullable = false, name="date")
    private LocalDate date;

    @Column(nullable = false, name="isDeleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "work")
    @JsonManagedReference
    private List<FileDB> filesDB;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name="work_tag",
          joinColumns = @JoinColumn(name="work_id"),
    inverseJoinColumns = @JoinColumn(name="tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "work", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JsonManagedReference
    private Set<Comment> comments = new HashSet<>();
}
