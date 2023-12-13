package com.server.socialBees.controller;

import com.server.socialBees.dto.FollowDTO;
import com.server.socialBees.dto.UserFollowInfoDTO;
import com.server.socialBees.entity.Follow;
import com.server.socialBees.entity.User;
import com.server.socialBees.repository.FollowRepository;
import com.server.socialBees.repository.UserRepository;
import com.server.socialBees.service.FollowService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/follow")
public class FollowController {
    private final FollowService followService;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    public FollowController(FollowService followService, FollowRepository followRepository, UserRepository userRepository){
        this.followService = followService;
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{followerId}/{followingId}")
    public ResponseEntity<Boolean> isUserFollowing(@PathVariable Long followerId, @PathVariable Long followingId){
       return new ResponseEntity<>(followService.isUserFollowing(followerId, followingId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createFollow(@RequestBody FollowDTO followDTO){
        Follow follow = new Follow();
        User follower = userRepository.findById(followDTO.getFollowerId()).get();
        User following = userRepository.findById(followDTO.getFollowingId()).get();

        follow.setFollower(follower);
        follow.setFollowing(following);

        followService.createFollow(follow);

        return new ResponseEntity<>("You have followed successfully!", HttpStatus.OK);
    }

    @GetMapping("/following/{followerId}")
    public ResponseEntity<List<UserFollowInfoDTO>> getFollowing(@PathVariable Long followerId) {
        List<User> usersIFollow = followService.getFollowing(followerId);

        ModelMapper modelMapper = new ModelMapper();

        List<UserFollowInfoDTO> userFollowInfos = usersIFollow.stream()
                .filter(user -> !user.isDeleted())
                .map(user -> modelMapper.map(user, UserFollowInfoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userFollowInfos);
    }

    @GetMapping("/followers/{followingId}")
    public ResponseEntity<List<UserFollowInfoDTO>> getFollowers(@PathVariable Long followingId) {
        List<User> followers = followService.getFollowers(followingId);

        ModelMapper modelMapper = new ModelMapper();

        List<UserFollowInfoDTO> userFollowInfos = followers.stream()
                .filter(user -> !user.isDeleted())
                .map(user -> modelMapper.map(user, UserFollowInfoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userFollowInfos);
    }

    @DeleteMapping("/unfollow/{userId}/{followingId}")
    public ResponseEntity<String> deleteFollow(@PathVariable Long userId, @PathVariable Long followingId){
        Follow follow = followRepository.findByFollowerIdAndFollowingId(userId, followingId);
        followService.deleteFollow(follow);

        return new ResponseEntity<>("Unfollowed successfully!", HttpStatus.OK);
    }

}
