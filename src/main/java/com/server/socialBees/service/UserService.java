package com.server.socialBees.service;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.User;
import com.server.socialBees.entity.Work;

import java.util.List;
import java.util.Set;

public interface UserService {
    User createUser(User user);
    User getUserById(Long userId);
    User getUserByEmail(String email);
    User updateUser(User newUser);
    void followTag(Long userId, Long tagId);
    void unfollowTag(Long userId, Long tagId);
    Set<Tag> getFollowedTags(Long userId);
    User deleteUserById(Long userId);
}
