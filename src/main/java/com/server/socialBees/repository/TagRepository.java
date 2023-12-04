package com.server.socialBees.repository;

import com.server.socialBees.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAll();
    Tag findTagById(Long tagId);
    @Query("SELECT t FROM Tag t WHERE t.name IN :tags")
    Set<Tag> assignTagsToSetFromList(@Param("tags") List<String> tags);
}
