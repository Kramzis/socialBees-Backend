package com.server.socialBees.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "funding")
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "amount")
    private Double amount;
    @Column(nullable = false, name = "status")
    private String status;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "funding", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Payment payment;
}
