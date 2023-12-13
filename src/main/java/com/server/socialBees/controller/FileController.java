package com.server.socialBees.controller;

import com.server.socialBees.entity.FileDB;
import com.server.socialBees.entity.Work;
import com.server.socialBees.message.ResponseFile;
import com.server.socialBees.message.ResponseMessage;
import com.server.socialBees.repository.FileRepository;
import com.server.socialBees.service.FileService;
import com.server.socialBees.service.WorkService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;
    private final FileRepository fileRepository;
    private final WorkService workService;

    public FileController(FileService fileService, FileRepository fileRepository, WorkService workService){
        this.fileService = fileService;
        this.fileRepository = fileRepository;
        this.workService = workService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("workId") Long workId) {
        String message;
        try {
            FileDB uploadedFile = fileService.store(file);

            Work work = workService.getWorkById(workId);

            uploadedFile.setWork(work);
            fileRepository.save(uploadedFile);

            fileService.store(file);

            message = "Uploaded the files successfully!";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
            List<ResponseFile> files = fileService.getAllFiles().map(dbFile -> {
                String fileDownloadUri = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/files/")
                        .path(String.valueOf(dbFile.getId()))
                        .toUriString();

                return new ResponseFile(
                        dbFile.getName(),
                        fileDownloadUri,
                        dbFile.getType(),
                        dbFile.getData().length);
            }).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = fileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}