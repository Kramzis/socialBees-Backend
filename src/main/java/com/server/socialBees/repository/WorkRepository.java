package com.server.socialBees.repository;

import com.server.socialBees.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Integer> {
    Work findWorkById(Integer workId);
}
