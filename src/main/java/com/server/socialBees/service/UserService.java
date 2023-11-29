package com.server.socialBees.service;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.User;
import com.server.socialBees.entity.Work;

import java.util.List;
import java.util.Set;

public interface UserService {
    User createUser(User user);
    User getUserById(Integer userId);
    User getUserByEmail(String email);
    User updateUser(User newUser);
    void followTag(Integer userId, Integer tagId);
    void unfollowTag(Integer userId, Integer tagId);
    List<Work> getWorksForUser(Integer userId);
    Set<Tag> getFollowedTags(Integer userId);
    User deleteUserById(Integer userId);
}
