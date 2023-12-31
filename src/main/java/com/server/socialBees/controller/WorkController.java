package com.server.socialBees.controller;

import com.server.socialBees.dto.WorkDTO;
import com.server.socialBees.entity.FileDB;
import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.User;
import com.server.socialBees.entity.Work;
import com.server.socialBees.repository.FileRepository;
import com.server.socialBees.repository.UserRepository;
import com.server.socialBees.service.FileService;
import com.server.socialBees.service.TagService;
import com.server.socialBees.service.WorkService;
import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/work")
public class WorkController extends BaseController{
    private final WorkService workService;
    private final FileService fileService;
    private final FileRepository fileRepository;
    private final TagService tagService;
    private final UserRepository userRepository;

    public WorkController(WorkService workService, FileService fileService, FileRepository fileRepository,
                          TagService tagService, UserRepository userRepository) {
        this.workService = workService;
        this.fileService = fileService;
        this.fileRepository = fileRepository;
        this.tagService = tagService;
        this.userRepository = userRepository;
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<String> createWork(@Valid @ModelAttribute WorkDTO workDTO) throws IOException {
        ModelMapper modelMapper = new ModelMapper();
        Work work = modelMapper.map(workDTO, Work.class);

        work.setId(null);

        User user = userRepository.findById(workDTO.getUserId()).get();
        work.setUser(user);

        Set<Tag> tags = tagService.assignTagsToSetFromList(workDTO.getTags());
        work.setTags(tags);

        FileDB file = new FileDB();
        if(workDTO.getFile() != null){
            file = fileService.store(workDTO.getFile());
            work.setFileDB(file);
        }

        Work savedWork = workService.createWork(work);

        if(workDTO.getFile() != null){
            file.setWork(savedWork);
            fileRepository.save(file);
        }

        return new ResponseEntity<>("Work added successfully!", HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{workId}")
    public ResponseEntity<String> updateWork(@Valid @ModelAttribute WorkDTO workDTO, @PathVariable Long workId) throws IOException {
        ModelMapper modelMapper = new ModelMapper();
        Work oldWork = modelMapper.map(workDTO, Work.class);
        oldWork.setId(workId);

        Set<Tag> tags = tagService.assignTagsToSetFromList(workDTO.getTags());
        oldWork.setTags(tags);

        FileDB newFile = new FileDB();
        if(workDTO.getFile() != null){
            newFile = fileService.store(workDTO.getFile());
            oldWork.setFileDB(newFile);
        }

        try{
            Work updatedWork =  workService.updateWork(oldWork);
            if(workDTO.getFile() != null){
                newFile.setWork(updatedWork);
                fileRepository.save(newFile);
            }
        }catch(DataIntegrityViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Work updated successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{workId}")
    public ResponseEntity<String> deleteWork(@PathVariable Long workId){
        workService.deleteWorkById(workId);

        return new ResponseEntity<>("Work deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/{workId}")
    public ResponseEntity<Work> getWork(@PathVariable Long workId) {
        Work work = workService.getWorkById(workId);
        if(work == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(work, HttpStatus.OK);
        }
    }

    @GetMapping("/{userId}/works")
    public ResponseEntity<List<Work>> getWorksForUser(@PathVariable Long userId){
        List<Work> userWorks = workService.getWorksForUser(userId);
        return new ResponseEntity<>(userWorks, HttpStatus.OK);
    }

    @GetMapping("/{userId}/worksByFollowedTags")
    public ResponseEntity<List<Work>> getWorksByFollowedTagsForUser(@PathVariable Long userId) {
        return new ResponseEntity<>(workService.getWorksByFollowedTagsForUser(userId), HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Work>> getRecentWork(){
        List<Work> recentWorks = workService.getRecentWorks();
        return new ResponseEntity<>(recentWorks, HttpStatus.OK);
    }
}