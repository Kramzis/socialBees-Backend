package com.server.socialBees.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="work")
public class Work {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 20, name="title")
    private String title;
    @Column(nullable = false, unique = true, length = 180, name="content")
    private String content;

    @OneToOne(mappedBy = "work", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private File file;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
