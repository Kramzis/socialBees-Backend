package com.server.socialBees.service;

import com.server.socialBees.entity.Follow;
import com.server.socialBees.entity.User;
import com.server.socialBees.repository.FollowRepository;
import com.server.socialBees.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService{
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowServiceImpl(FollowRepository followRepository, UserRepository userRepository){
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Follow createFollow(Follow follow) {
        return followRepository.save(follow);
    }

    @Override
    public List<User> getFollowing(Integer followerId) {
        List<Follow> follows = followRepository.findByFollower(userRepository.findUserById(followerId));
        return follows.stream().map(Follow::getFollowing).collect(Collectors.toList());
    }

    @Override
    public List<User> getFollowers(Integer followingId) {
        List<Follow> followers = followRepository.findByFollowing(userRepository.findUserById(followingId));
        return followers.stream().map(Follow::getFollower).collect(Collectors.toList());
    }

    @Override
    public void deleteFollow(Follow follow) {
        followRepository.delete(follow);
    }

}
