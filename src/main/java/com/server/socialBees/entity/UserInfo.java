package com.server.socialBees.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="userInfo")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = false, name="surname")
    private String surname;

    @Column(name="birthday")
    private LocalDate birthday;

    @OneToOne
    @JsonBackReference
    private User user;
}
