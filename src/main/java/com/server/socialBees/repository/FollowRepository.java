package com.server.socialBees.repository;

import com.server.socialBees.entity.Follow;
import com.server.socialBees.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT COUNT(f) > 0 FROM Follow f WHERE f.follower.id = :followerId AND f.following.id = :followingId")
    boolean isUserFollowing(@Param("followerId") Long followerId,
                            @Param("followingId") Long followingId);
    List<Follow> findByFollower(User follower);
    List<Follow> findByFollowing(User following);
    Follow findByFollowerIdAndFollowingId(Long followerId, Long followingId);
}
