package com.server.socialBees.controller;

import com.server.socialBees.dto.FollowDTO;
import com.server.socialBees.entity.Follow;
import com.server.socialBees.entity.User;
import com.server.socialBees.repository.FollowRepository;
import com.server.socialBees.repository.UserRepository;
import com.server.socialBees.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowService followService;

    @Autowired
    private FollowRepository followRepository;

    @PostMapping()
    public ResponseEntity<Follow> createFollow(@RequestBody FollowDTO followDTO){
        User follower = userRepository.findUserById(followDTO.getFollowerId());
        User following = userRepository.findUserById(followDTO.getFollowingId());

        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);

        return new ResponseEntity<>(followService.createFollow(follow), HttpStatus.OK);
    }

    @GetMapping("/following/{followerId}")
    public ResponseEntity<List<User>> getFollowing(@PathVariable Integer followerId) {
        List<User> usersIFollow = followService.getFollowing(followerId);
        return ResponseEntity.ok(usersIFollow);
    }

    @GetMapping("/followers/{followingId}")
    public ResponseEntity<List<User>> getFollowers(@PathVariable Integer followingId) {
        List<User> followers = followService.getFollowers(followingId);
        return ResponseEntity.ok(followers);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteFollow(@RequestBody FollowDTO followDTO){
        Follow follow = followRepository.findByFollowerIdAndFollowingId(followDTO.getFollowerId(), followDTO.getFollowingId());
        followService.deleteFollow(follow);

        return new ResponseEntity<>("Unfollowed successfully!", HttpStatus.OK);
    }

}
