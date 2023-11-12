package com.server.socialBees.repository;

import com.server.socialBees.entity.UserInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
}
