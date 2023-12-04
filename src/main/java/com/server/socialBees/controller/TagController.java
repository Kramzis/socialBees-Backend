package com.server.socialBees.controller;

import com.server.socialBees.entity.Tag;
import com.server.socialBees.service.TagService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @Transactional
    @GetMapping()
    public ResponseEntity<List<Tag>> getAllTags(){
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }
}
