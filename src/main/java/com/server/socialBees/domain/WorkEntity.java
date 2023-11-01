package com.server.socialBees.domain;

import jakarta.persistence.*;

@Entity
@Table(name="work")
public class WorkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="title")
    private String title;
    @Column(name="content")
    private String content;

    //osobna klasa
    @Column(name="fileAttachment")
    private String fileAttachment;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public WorkEntity(Integer id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

}
