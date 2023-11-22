package com.server.socialBees.service;

import com.server.socialBees.entity.FileDB;
import com.server.socialBees.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<FileDB> store(List<MultipartFile> files) throws IOException {
        List<FileDB> fileDBList = new ArrayList<>();

        if(!Objects.equals(files.get(0).getOriginalFilename(), "")){
            for (MultipartFile file : files) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                FileDB fileDB = new FileDB(null, fileName, file.getContentType(), file.getBytes(), null);
                fileDBList.add(fileRepository.save(fileDB));
            }
        }

        return fileDBList;
    }

    @Override
    public List<FileDB> updateFiles(List<MultipartFile> addedFiles, Integer workId) throws IOException {
        List<FileDB> formerFiles = fileRepository.findAllByWorkId(workId);

        fileRepository.deleteAll(formerFiles);

        return this.store(addedFiles);
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
