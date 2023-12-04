package com.server.socialBees.service;

import com.server.socialBees.entity.Follow;
import com.server.socialBees.entity.User;

import java.util.List;

public interface FollowService {
    boolean isUserFollowing(Long followerId, Long followingId);
    Follow createFollow(Follow follow);
    List<User> getFollowers(Long followingId);
    List<User> getFollowing(Long followerId);
    Long getNumberOfFollowersForUser(Long userId);
    Long getNumberOfFollowingForUser(Long userId);
    void deleteFollow(Follow follow);
}
