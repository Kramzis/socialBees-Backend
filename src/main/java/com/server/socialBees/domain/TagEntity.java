package com.server.socialBees.domain;

import jakarta.persistence.*;
import org.hibernate.jdbc.Work;

@Entity
@Table(name="tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="work_id", nullable=false)
    private WorkEntity work;
    //???
    @Column(name="ifFollowed")
    private boolean ifFollowed;

}
