package com.server.socialBees.repository;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface WorkRepository extends JpaRepository<Work, Long> {
    Work findWorkById(Long workId);
    List<Work> findByTagsIn(Set<Tag> tags);
    @Query("SELECT w FROM Work w WHERE w.date >= :last30Days")
    List<Work> findRecentWorks(LocalDate last30Days);
}
