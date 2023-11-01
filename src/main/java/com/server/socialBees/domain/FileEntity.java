package com.server.socialBees.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name ="file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="file_id")
    private Integer id;
    @Column(name ="name")
    private String name;
    @Column(name ="type")
    private String type;

    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB")
    private byte[] data;

    @OneToOne
    @MapsId
    @JoinColumn(name = "work_id")
    private WorkEntity work;

    public FileEntity() {
    }

    public FileEntity(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}