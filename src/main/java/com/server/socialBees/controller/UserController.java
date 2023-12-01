package com.server.socialBees.controller;

import com.server.socialBees.dto.RegisterRequest;
import com.server.socialBees.dto.UserDTO;
import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.User;
import com.server.socialBees.entity.Work;
import com.server.socialBees.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;

    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody RegisterRequest registerRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Converter<String, LocalDate> birthdayConverter = context -> {String source = context.getSource();
            if (source == null) {
                return null;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(source, formatter);
            };

        TypeMap<RegisterRequest, User> typeMap = modelMapper.createTypeMap(RegisterRequest.class, User.class);
        typeMap.addMappings(mapper -> mapper.using(birthdayConverter).map(RegisterRequest::getBirthday, User::setBirthday));

        User user = modelMapper.map(registerRequest, User.class);

        userService.createUser(user);

        return new ResponseEntity<>("User created successfully!",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            User user = userService.getUserById(id);

            UserDTO userDTO = modelMapper.map(user, UserDTO.class);

            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        try {
            ModelMapper modelMapper = new ModelMapper();

            User updatedUser = modelMapper.map(userDTO, User.class);

            updatedUser.setId(id);

            userService.updateUser(updatedUser);

            return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);

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

    @GetMapping("/{userId}/works")
    public ResponseEntity<List<Work>> getWorksForUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getWorksForUser(userId), HttpStatus.OK);
    }
}
