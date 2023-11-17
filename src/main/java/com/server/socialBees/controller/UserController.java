package com.server.socialBees.controller;

import com.server.socialBees.dto.UserDTO;
import com.server.socialBees.entity.User;
import com.server.socialBees.entity.UserInfo;
import com.server.socialBees.service.UserInfoService;
import com.server.socialBees.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Transactional
    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {

        // Utworzenie obiektu User
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setDeleted(false);

        // Ustawienie innych danych użytkownika

        LocalDate birthday = LocalDate.parse(userDTO.getBirthday());

        // Utworzenie obiektu UserInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setName(userDTO.getName());
        userInfo.setSurname(userDTO.getSurname());
        userInfo.setBirthday(birthday);

        // Ustawienie relacji jeden do jeden między User a UserInfo
        user.setUserInfo(userInfo);
        userInfo.setUser(user);

        User createdUser = userService.createUser(user);
        UserInfo createdUserInfo = userInfoService.createUserInfo(userInfo);

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


}
