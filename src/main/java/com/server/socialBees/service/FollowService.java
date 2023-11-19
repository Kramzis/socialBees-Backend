package com.server.socialBees.service;

import com.server.socialBees.entity.Follow;
import com.server.socialBees.entity.User;

import java.util.List;

public interface FollowService {
    Follow createFollow(Follow follow);
    List<User> getFollowers(Integer followingId);
    List<User> getFollowing(Integer followerId);
    void deleteFollow(Follow follow);
}
