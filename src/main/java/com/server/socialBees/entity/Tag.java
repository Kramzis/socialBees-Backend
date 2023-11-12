package com.server.socialBees.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tag")
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = true, unique = true, name="name")
    private String name;
    @Column(nullable = false, name="ifFollowed")
    private boolean ifFollowed;

    @ManyToOne
    @JoinColumn(name="work_id", nullable=false)
    private Work work;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
