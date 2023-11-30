package com.server.socialBees.controller;

import com.server.socialBees.service.TagService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;
    public TagController(TagService tagService){
        this.tagService = tagService;
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id){
        tagService.deleteTagById(id);

        return new ResponseEntity<>("Tag deleted successfully!", HttpStatus.OK);
    }
}
