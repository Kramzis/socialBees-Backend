package com.server.socialBees.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "accountData")
public class AccountDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name="accountNumber")
    private Integer accountNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public AccountDataEntity(){}
    public AccountDataEntity(Integer id, Integer accountNumber, UserEntity user) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.user = user;
    }

}
