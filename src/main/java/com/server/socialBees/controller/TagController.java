package com.server.socialBees.controller;

import com.server.socialBees.service.TagService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        tagService.deleteTagBy(id);

        return new ResponseEntity<>("Tag deleted successfully!", HttpStatus.OK);
    }
}
