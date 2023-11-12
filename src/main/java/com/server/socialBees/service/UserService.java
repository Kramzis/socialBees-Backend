package com.server.socialBees.service;

import com.server.socialBees.entity.User;

import java.time.LocalDate;
import java.util.Date;

public interface UserService {
    User createUser(User user);
    void deleteUserById(Integer userId);
}
