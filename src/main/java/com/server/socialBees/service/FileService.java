package com.server.socialBees.service;

import com.server.socialBees.entity.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface FileService {
    List<FileDB> store(List<MultipartFile> files) throws IOException;
    List<FileDB> updateFiles(List<MultipartFile> addedFiles, Integer workId) throws IOException;
    FileDB getFile(String id);
    Stream<FileDB> getAllFiles();
}
