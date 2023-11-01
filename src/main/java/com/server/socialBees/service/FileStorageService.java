package com.server.socialBees.service;

import com.server.socialBees.domain.FileEntity;
import com.server.socialBees.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {
    @Autowired
    private FileRepository fileDBRepository;

    public FileEntity store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileEntity FileEntity = new FileEntity(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileEntity);
    }

    public FileEntity getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileEntity> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
