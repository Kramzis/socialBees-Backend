package com.server.socialBees.repository;

import com.server.socialBees.entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileDB, String> {
    FileDB findFileByWorkId(Long workId);
}
