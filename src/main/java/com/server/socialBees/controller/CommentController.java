package com.server.socialBees.controller;

import com.server.socialBees.dto.CommentDTO;
import com.server.socialBees.entity.Comment;
import com.server.socialBees.service.CommentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/comment")
public class CommentController extends BaseController{
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<String> createComment(@Valid @RequestBody CommentDTO commentDTO){
        ModelMapper modelMapper = new ModelMapper();
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        commentService.createComment(comment);
        return new ResponseEntity<>("Comment added successfully!", HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        if(comment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
    }

    @Transactional
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable Long commentId){
        ModelMapper modelMapper = new ModelMapper();
        Comment updatedComment = modelMapper.map(commentDTO, Comment.class);

        updatedComment.setId(commentId);

        commentService.updateComment(updatedComment);

        return new ResponseEntity<>("Comment updated successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId){
        commentService.deleteCommentById(commentId);

        return new ResponseEntity<>("Comment deleted successfully!", HttpStatus.OK);
    }
}
