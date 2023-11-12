package com.server.socialBees.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accountData")
public class AccountData {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, name="accountNumber")
    private Integer accountNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

}
