package com.server.socialBees.controller;

import com.server.socialBees.dto.CommentDTO;
import com.server.socialBees.entity.Comment;
import com.server.socialBees.service.CommentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<String> createComment(@RequestBody CommentDTO commentDTO){
        ModelMapper modelMapper = new ModelMapper();
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        commentService.createComment(comment);
        return new ResponseEntity<>("Comment added successfully!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        if(comment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(@RequestBody CommentDTO commentDTO, @PathVariable Long id){
        ModelMapper modelMapper = new ModelMapper();
        Comment updatedComment = modelMapper.map(commentDTO, Comment.class);

        updatedComment.setId(id);

        commentService.updateComment(updatedComment);

        return new ResponseEntity<>("Comment updated successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id){
        commentService.deleteCommentById(id);

        return new ResponseEntity<>("Comment deleted successfully!", HttpStatus.OK);
    }
}
