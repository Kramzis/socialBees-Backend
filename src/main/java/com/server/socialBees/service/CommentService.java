package com.server.socialBees.service;

import com.server.socialBees.entity.Comment;

public interface CommentService {
    Comment createComment(Comment comment);
    Comment getCommentById(Long commentId);
    Comment updateComment(Comment newComment);
    Comment deleteCommentById(Long commentId);
}
