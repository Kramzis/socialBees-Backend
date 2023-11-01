package com.server.socialBees.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "method")
    private String method;
    @Column(name = "status")
    private String status;
    @Column(name = "date")
    private Date date;
}
