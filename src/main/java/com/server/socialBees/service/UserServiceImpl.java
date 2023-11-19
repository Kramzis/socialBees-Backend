package com.server.socialBees.service;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.User;
import com.server.socialBees.entity.Work;
import com.server.socialBees.repository.TagRepository;
import com.server.socialBees.repository.UserRepository;
import com.server.socialBees.repository.WorkRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final WorkRepository workRepository;
    public UserServiceImpl(UserRepository userRepository, WorkRepository workRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.workRepository = workRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUserBy(Integer userId){
        User user = userRepository.findUserById(userId);
        if(user.isDeleted()){
           return null;
        } else {
         return user;
        }
    }

    @Override
    @Transactional
    public User updateUser(User newUser){
        User user = userRepository.findUserById(newUser.getId());
        if(user != null){
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public User deleteUserBy(Integer userId)
    {
        User user = userRepository.findUserById(userId);
        user.setDeleted(true);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void followTag(Integer userId, Integer tagId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));

        user.getTags().add(tag);
        userRepository.save(user);
    }
    @Override
    @Transactional
    public void unfollowTag(Integer userId, Integer tagId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));

        user.getTags().remove(tag);
        userRepository.save(user);
    }

    @Override
    public List<Work> getWorksForUser(Integer userId) {
        User user = userRepository.findUserById(userId);
        Set<Tag> tags = user.getTags();
        return workRepository.findByTagsIn(tags);
    }

    @Override
    public Set<Tag> getFollowedTags(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getTags();
    }

}

