package com.server.socialBees.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="fileDB")
public class FileDB {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name ="name")
    private String name;

    @Column(nullable = false, name ="type")
    private String type;

    @Lob
    @Column(nullable = false, name = "data", columnDefinition = "LONGBLOB", length = 2147483647)
    private byte[] data;

    @OneToOne(optional = true)
    @JsonBackReference
    private Work work;

}