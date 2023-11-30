package com.server.socialBees.repository;

import com.server.socialBees.entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileDB, String> {
    List<FileDB> findAllByWorkId(Long workId);
}
