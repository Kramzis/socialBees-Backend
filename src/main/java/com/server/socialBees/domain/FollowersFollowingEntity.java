package com.server.socialBees.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="followersFollowing")
public class FollowersFollowingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private UserEntity follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private UserEntity following;

    public FollowersFollowingEntity(UserEntity follower, UserEntity following) {
        this.follower = follower;
        this.following = following;
    }

}
