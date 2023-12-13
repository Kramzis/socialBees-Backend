package com.server.socialBees.controller;

import com.server.socialBees.dto.RegisterRequest;
import com.server.socialBees.dto.UserDTO;
import com.server.socialBees.dto.UserStatsDTO;
import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.User;
import com.server.socialBees.service.FollowService;
import com.server.socialBees.service.UserService;
import com.server.socialBees.service.WorkService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/user")
public class UserController extends BaseController{
    private final UserService userService;
    private final FollowService followService;
    private final WorkService workService;

    public UserController(UserService userService, FollowService followService, WorkService workService){
        this.userService = userService;
        this.followService = followService;
        this.workService = workService;

    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody RegisterRequest registerRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Converter<String, LocalDate> birthdayConverter = context -> {String source = context.getSource();
            if (source == null) {
                return null;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(source, formatter);
            };

        TypeMap<RegisterRequest, User> typeMap = modelMapper.createTypeMap(RegisterRequest.class, User.class);
        typeMap.addMappings(mapper -> mapper.using(birthdayConverter).map(RegisterRequest::getBirthday, User::setBirthday));

        User user = modelMapper.map(registerRequest, User.class);

        userService.createUser(user);

        return new ResponseEntity<>("User created successfully!",HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            User user = userService.getUserById(userId);

            UserDTO userDTO = modelMapper.map(user, UserDTO.class);

            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long userId){
        try {
            ModelMapper modelMapper = new ModelMapper();

            User updatedUser = modelMapper.map(userDTO, User.class);

            updatedUser.setId(userId);

            userService.updateUser(updatedUser);

            return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/stats")
    public ResponseEntity<UserStatsDTO> getUserStats(@PathVariable Long userId){
        UserStatsDTO userStatsDTO = new UserStatsDTO(
                workService.getNumberOfWorksForUser(userId),
                followService.getNumberOfFollowersForUser(userId),
                followService.getNumberOfFollowingForUser(userId));
        return new ResponseEntity<>(userStatsDTO, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userService.deleteUserById(userId);

        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }

    @PostMapping("/{userId}/follow-tag/{tagId}")
    public ResponseEntity<String> followTag(@PathVariable Long userId, @PathVariable Long tagId) {
        userService.followTag(userId, tagId);
        return new ResponseEntity<>("Tag followed successfully!", HttpStatus.OK);
    }

    @GetMapping("/{userId}/tags")
    public ResponseEntity<Set<Tag>> getFollowedTags(@PathVariable Long userId){
        Set<Tag> tags = userService.getFollowedTags(userId);

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/unfollow-tag/{tagId}")
    public ResponseEntity<String> unfollowTag(@PathVariable Long userId, @PathVariable Long tagId) {
        userService.unfollowTag(userId, tagId);
        return new ResponseEntity<>("Tag unfollowed successfully!", HttpStatus.OK);
    }
}
