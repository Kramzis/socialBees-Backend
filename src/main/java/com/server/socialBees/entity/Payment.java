package com.server.socialBees.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "method")
    private String method;

    @Column(nullable = false, name = "status")
    private String status;

    @Column(nullable = false, name = "date")
    private Date date;

    @OneToOne
    @MapsId
    @JoinColumn(name = "funding_id")
    private Funding funding;
}
