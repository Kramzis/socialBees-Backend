package com.server.socialBees.controller;

import com.server.socialBees.dto.UserDTO;
import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.User;
import com.server.socialBees.entity.UserInfo;
import com.server.socialBees.entity.Work;
import com.server.socialBees.service.UserInfoService;
import com.server.socialBees.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserInfoService userInfoService;

    public UserController(UserService userService, UserInfoService userInfoService){
        this.userService = userService;
        this.userInfoService = userInfoService;
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setDeleted(false);

        LocalDate birthday = LocalDate.parse(userDTO.getBirthday());

        UserInfo userInfo = new UserInfo();
        userInfo.setName(userDTO.getName());
        userInfo.setSurname(userDTO.getSurname());
        userInfo.setBirthday(birthday);

        user.setUserInfo(userInfo);
        userInfo.setUser(user);

        userService.createUser(user);
        userInfoService.createUserInfo(userInfo);

        return new ResponseEntity<>("User created successfully!",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = userService.getUserBy(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer id){
        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setUsername(userDTO.getUsername());
        updatedUser.setEmail(userDTO.getEmail());
        updatedUser.setPassword(userDTO.getPassword());

        userService.updateUser(updatedUser);

        return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        userService.deleteUserBy(id);

        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }

    @PostMapping("/{userId}/follow-tag/{tagId}")
    public ResponseEntity<String> followTag(@PathVariable Integer userId, @PathVariable Integer tagId) {
        userService.followTag(userId, tagId);
        return new ResponseEntity<>("Tag followed successfully!", HttpStatus.OK);
    }

    @GetMapping("/{userId}/tags")
    public ResponseEntity<Set<Tag>> getFollowedTags(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getFollowedTags(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/unfollow-tag/{tagId}")
    public ResponseEntity<String> unfollowTag(@PathVariable Integer userId, @PathVariable Integer tagId) {
        userService.unfollowTag(userId, tagId);
        return new ResponseEntity<>("Tag unfollowed successfully!", HttpStatus.OK);
    }

    @GetMapping("/{userId}/works")
    public ResponseEntity<List<Work>> getWorksForUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.getWorksForUser(userId), HttpStatus.OK);
    }

}
