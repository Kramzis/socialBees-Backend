package com.server.socialBees.controller;

import com.server.socialBees.dto.CommentDTO;
import com.server.socialBees.entity.Comment;
import com.server.socialBees.entity.User;
import com.server.socialBees.entity.Work;
import com.server.socialBees.repository.UserRepository;
import com.server.socialBees.repository.WorkRepository;
import com.server.socialBees.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final UserRepository userRepository;
    private final WorkRepository workRepository;

    public CommentController(CommentService commentService, UserRepository userRepository, WorkRepository workRepository){
        this.commentService = commentService;
        this.userRepository = userRepository;
        this.workRepository = workRepository;
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<String> createComment(@RequestBody CommentDTO commentDTO){
        User user = userRepository.findUserById(commentDTO.getUserId());
        Work work = workRepository.findWorkById(commentDTO.getWorkId());

        commentDTO.setDate(LocalDate.now());

        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setDate(commentDTO.getDate());
        comment.setDeleted(false);
        comment.setUser(user);
        comment.setWork(work);


        commentService.createComment(comment);
        return new ResponseEntity<>("Comment added successfully!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Integer id) {
        Comment comment = commentService.getCommentBy(id);
        if(comment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer id){
        Comment updatedComment = new Comment();
        updatedComment.setId(id);
        updatedComment.setContent(commentDTO.getContent());
        updatedComment.setDate(commentDTO.getDate());

        commentService.updateComment(updatedComment);

        return new ResponseEntity<>("Comment updated successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer id){
        commentService.deleteCommentBy(id);

        return new ResponseEntity<>("Comment deleted successfully!", HttpStatus.OK);
    }
}
