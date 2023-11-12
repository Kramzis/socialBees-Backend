package com.server.socialBees.service;

import com.server.socialBees.entity.User;
import com.server.socialBees.entity.UserInfo;
import com.server.socialBees.repository.UserInfoRepository;
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
    public void deleteUserById(Integer userId)
    {
        userRepository.deleteById(userId);
    }
}

