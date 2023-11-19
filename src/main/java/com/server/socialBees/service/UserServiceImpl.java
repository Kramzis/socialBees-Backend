package com.server.socialBees.service;

import com.server.socialBees.entity.User;
import com.server.socialBees.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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

//    @Transactional
//    public void follow(Long userId, Long toFollowId) {
//        User user = userRepository.findById(userId);
//        User toFollow = userRepository.findById(toFollowId);
//        user.addFollower(toFollow);
//    }
//    @Override
//    @Transactional
//    public void unfollow(Integer userId, Integer toUnfollowId) {
//        User user = userRepository.findById(userId);
//        User toFollow = userRepository.findById(toUnfollowId);
//        user.removeFollower(toFollow);
//    }

}

