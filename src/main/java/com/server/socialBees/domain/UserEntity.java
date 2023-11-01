package com.server.socialBees.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name="id")
    private Integer id;
    @Setter
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="birthday")
    private Date birthday;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="role")
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AccountDataEntity accountData;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<CommentEntity> comments;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<WorkEntity> works;

    public UserEntity(){};
    public UserEntity(Integer id, String name, String surname, Date birthday, String username, String password, String email, String role){
        this.id =  id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;

    }
}
