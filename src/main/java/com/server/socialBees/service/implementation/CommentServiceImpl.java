package com.server.socialBees.service.implementation;

import com.server.socialBees.entity.Comment;
import com.server.socialBees.repository.CommentRepository;
import com.server.socialBees.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public Comment createComment(Comment comment){return commentRepository.save(comment);}

    @Override
    @Transactional
    public Comment getCommentById(Long commentId){
        Comment comment = commentRepository.findCommentById(commentId);
        if(comment.isDeleted()){
            return null;
        } else {
            return comment;
        }
    }

    @Override
    @Transactional
    public Comment updateComment(Comment newComment){
        Comment comment = commentRepository.findCommentById(newComment.getId());
        if(comment != null){
            comment.setContent(newComment.getContent());
            return commentRepository.save(comment);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Comment deleteCommentById(Long commentId){
        Comment comment = commentRepository.findCommentById(commentId);
        comment.setDeleted(true);

        return commentRepository.save(comment);
    }
}
