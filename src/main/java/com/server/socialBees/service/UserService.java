package com.server.socialBees.service;

import com.server.socialBees.entity.User;

public interface UserService {
    User createUser(User user);
    User getUserBy(Integer userId);
    User updateUser(User newUser);
    User deleteUserBy(Integer userId);
}
