package com.server.socialBees.repository;

import com.server.socialBees.entity.Follow;
import com.server.socialBees.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    List<Follow> findByFollower(User follower);
    List<Follow> findByFollowing(User following);
}
