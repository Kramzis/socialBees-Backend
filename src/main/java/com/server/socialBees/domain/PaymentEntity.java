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
    @Column(name = "payment_id")
    private Integer id;
    @Column(name = "method")
    private String method;
    @Column(name = "status")
    private String status;
    @Column(name = "date")
    private Date date;

    @OneToOne
    @MapsId
    @JoinColumn(name = "funding_id")
    private FundingEntity funding;

    public PaymentEntity(Integer id, String method, String status, Date date){
        this.id = id;
        this.method = method;
        this.status = status;
        this.date = date;
    }
}
