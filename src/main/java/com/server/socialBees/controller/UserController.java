package com.server.socialBees.controller;

import com.server.socialBees.UserDTO;
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
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Transactional
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {

        // Utworzenie obiektu User
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
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

        return new ResponseEntity<>("Użytkownik stworzony pomyślnie!",HttpStatus.OK) ;
    }

}
