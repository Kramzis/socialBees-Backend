package com.server.socialBees.controller;

import com.server.socialBees.dto.WorkDTO;
import com.server.socialBees.entity.Tag;
import com.server.socialBees.entity.User;
import com.server.socialBees.entity.Work;
import com.server.socialBees.repository.UserRepository;
import com.server.socialBees.service.TagService;
import com.server.socialBees.service.WorkService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@Controller
@RequestMapping("/work")
public class WorkController {
    @Autowired
    private WorkService workService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping()
    public ResponseEntity<String> createWork(@RequestBody WorkDTO workDTO){
        User user = userRepository.findUserById(workDTO.getUserId());

        workDTO.setDate(LocalDate.now());

        Work work = new Work();
        work.setTitle(workDTO.getTitle());
        work.setContent(workDTO.getContent());
        work.setDate(workDTO.getDate());
        work.setDeleted(false);
        work.setUser(user);

        Set<Tag> tags = tagService.assignTagsToSetFromList(workDTO.getTags());
        work.setTags(tags);

        //work.setUser();

        Work createdWork = workService.createWork(work);
//        userRepository.save(user);
        return new ResponseEntity<>("Work added successfully!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Work> getWork(@PathVariable Integer id) {
        Work work = workService.getWorkBy(id);
        if(work == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(work, HttpStatus.OK);
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWork(@RequestBody WorkDTO workDTO, @PathVariable Integer id){
        Work updatedWork = new Work();
        updatedWork.setId(id);
        updatedWork.setTitle(workDTO.getTitle());
        updatedWork.setContent(workDTO.getContent());
        updatedWork.setDate(workDTO.getDate());

        Set<Tag> tags = tagService.assignTagsToSetFromList(workDTO.getTags());
        updatedWork.setTags(tags);

        //dodaj pliki

        workService.updateWork(updatedWork);

        return new ResponseEntity<>("Work updated successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWork(@PathVariable Integer id){
        workService.deleteWorkBy(id);

        return new ResponseEntity<>("Work deleted successfully!", HttpStatus.OK);
    }
}
