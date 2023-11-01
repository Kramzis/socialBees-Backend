package com.server.socialBees.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "funding")
public class FundingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="funding_id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="amount")
    private Double amount;
    @Column(name="status")
    private String status;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(mappedBy = "funding", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PaymentEntity payment;

    public FundingEntity(){}

    public FundingEntity(Integer id, String name, Double amount, String status){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.status = status;
    }
}
