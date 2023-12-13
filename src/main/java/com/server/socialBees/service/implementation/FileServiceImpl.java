package com.server.socialBees.service.implementation;

import com.server.socialBees.entity.FileDB;
import com.server.socialBees.repository.FileRepository;
import com.server.socialBees.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(null, fileName, file.getContentType(), file.getBytes(), null);
        fileRepository.save(fileDB);
        return fileDB;
    }

    @Override
    public FileDB updateFile(MultipartFile addedFile, Long workId) throws IOException {
        FileDB formerFile = fileRepository.findFileByWorkId(workId);

        fileRepository.delete(formerFile);

        return this.store(addedFile);
    }

    @Override
    public FileDB getFile(String id) {
        return fileRepository.findById(id).get();
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return fileRepository.findAll().stream();
    }
}
