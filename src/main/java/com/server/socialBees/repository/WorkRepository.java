package com.server.socialBees.repository;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface WorkRepository extends JpaRepository<Work, Long> {
    Work findWorkById(Long workId);
    List<Work> findByTagsIn(Set<Tag> tags);
}
