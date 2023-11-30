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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
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

    @PostMapping()
    public ResponseEntity<String> createFollow(@RequestBody FollowDTO followDTO){
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<FollowDTO, Follow> typeMap = modelMapper.createTypeMap(FollowDTO.class, Follow.class);

        typeMap.addMappings(mapper -> mapper.using(ctx -> userRepository.findUserById(followDTO.getFollowerId()))
                .map(FollowDTO::getFollowerId, Follow::setFollower));

        typeMap.addMappings(mapper -> mapper.using(ctx -> userRepository.findUserById(followDTO.getFollowingId()))
                .map(FollowDTO::getFollowingId, Follow::setFollowing));

        Follow follow = modelMapper.map(followDTO, Follow.class);

        followService.createFollow(follow);

        return new ResponseEntity<>("You have followed successfully!", HttpStatus.OK);
    }

    @GetMapping("/following/{followerId}")
    public ResponseEntity<List<UserFollowInfoDTO>> getFollowing(@PathVariable Long followerId) {
        List<User> usersIFollow = followService.getFollowing(followerId);

        ModelMapper modelMapper = new ModelMapper();

        List<UserFollowInfoDTO> userFollowInfos = usersIFollow.stream()
                .map(user -> modelMapper.map(user, UserFollowInfoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userFollowInfos);
    }

    @GetMapping("/followers/{followingId}")
    public ResponseEntity<List<UserFollowInfoDTO>> getFollowers(@PathVariable Long followingId) {
        List<User> followers = followService.getFollowers(followingId);

        ModelMapper modelMapper = new ModelMapper();

        List<UserFollowInfoDTO> userFollowInfos = followers.stream()
                .map(user -> modelMapper.map(user, UserFollowInfoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userFollowInfos);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteFollow(@RequestBody FollowDTO followDTO){
        Follow follow = followRepository.findByFollowerIdAndFollowingId(followDTO.getFollowerId(), followDTO.getFollowingId());
        followService.deleteFollow(follow);

        return new ResponseEntity<>("Unfollowed successfully!", HttpStatus.OK);
    }

}
