package com.server.socialBees.repository;

import com.server.socialBees.entity.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDataRepository extends JpaRepository<AccountData, Integer> {
}
