package com.server.socialBees.service.implementation;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.User;
import com.server.socialBees.entity.Work;
import com.server.socialBees.repository.TagRepository;
import com.server.socialBees.repository.UserRepository;
import com.server.socialBees.repository.WorkRepository;
import com.server.socialBees.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final WorkRepository workRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, WorkRepository workRepository, TagRepository tagRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.workRepository = workRepository;
        this.tagRepository = tagRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User createUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUserById(Long userId){
        User user = userRepository.findUserById(userId);
        if(user.isDeleted()){
           return null;
        } else {
         return user;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);

        if (user != null && !user.isDeleted()){
            return user;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public User updateUser(User newUser){
        User user = userRepository.findUserById(newUser.getId());
        if(user != null){
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public User deleteUserById(Long userId)
    {
        User user = userRepository.findUserById(userId);
        user.setDeleted(true);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void followTag(Long userId, Long tagId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));

        user.getTags().add(tag);
        userRepository.save(user);
    }

    @Override
    public Set<Tag> getFollowedTags(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getTags();
    }

    @Override
    @Transactional
    public void unfollowTag(Long userId, Long tagId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));

        user.getTags().remove(tag);
        userRepository.save(user);
    }

    @Override
    public List<Work> getWorksForUser(Long userId) {
        User user = userRepository.findUserById(userId);
        Set<Tag> tags = user.getTags();
        return workRepository.findByTagsIn(tags);
    }

}

