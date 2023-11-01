package com.server.socialBees.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="workTags")
public class WorkTagsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "work_id")
    private WorkEntity work;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private TagEntity tag;

    public WorkTagsEntity(WorkEntity work, TagEntity tag) {
        this.work = work;
        this.tag = tag;
    }
}
