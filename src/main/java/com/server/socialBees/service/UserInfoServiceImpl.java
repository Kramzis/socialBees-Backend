package com.server.socialBees.service;

import com.server.socialBees.entity.UserInfo;
import com.server.socialBees.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    UserInfoRepository userInfoRepository;

    @Override
    public UserInfo createUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }
}
