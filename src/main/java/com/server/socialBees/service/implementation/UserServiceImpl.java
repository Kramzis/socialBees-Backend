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

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, TagRepository tagRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User getUserById(Long userId){
        User user = userRepository.findById(userId).get();

        if(!user.isDeleted()){
            return user;
        } else {
            throw new NoSuchElementException("User not found!");
        }
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);

        if(user == null){
            throw new NoSuchElementException("User not found!");
        } else {
            if(user.isDeleted()){
                throw new NoSuchElementException("User not found!");
            } else {
                return user;
            }
        }
    }

    @Override
    @Transactional
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User newUser){
        User user = userRepository.findById(newUser.getId()).get();

        if(!user.isDeleted()){
            user.setUsername(newUser.getUsername());
            user.setName(newUser.getName());
            user.setSurname(newUser.getSurname());
            user.setEmail(newUser.getEmail());
            user.setBirthday(newUser.getBirthday());
            return userRepository.save(user);
        } else {
            throw new NoSuchElementException("User not found!");
        }
    }

    @Override
    public User deleteUserById(Long userId)
    {
        User user = userRepository.findById(userId).get();
        user.setDeleted(true);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void followTag(Long userId, Long tagId) {
        User user = userRepository.findById(userId).get();
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));

        user.getTags().add(tag);
        userRepository.save(user);
    }

    @Override
    public Set<Tag> getFollowedTags(Long userId) {
        User user = userRepository.findById(userId).get();
        return user.getTags();
    }

    @Override
    @Transactional
    public void unfollowTag(Long userId, Long tagId) {
        User user = userRepository.findById(userId).get();
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));

        user.getTags().remove(tag);
        userRepository.save(user);
    }

}

