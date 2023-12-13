package com.server.socialBees.service;

import com.server.socialBees.entity.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileService {
    FileDB store(MultipartFile file) throws IOException;
    FileDB updateFile(MultipartFile addedFile, Long workId) throws IOException;
    FileDB getFile(String id);
    Stream<FileDB> getAllFiles();
}
