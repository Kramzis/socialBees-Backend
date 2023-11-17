package com.server.socialBees.service;

import com.server.socialBees.entity.Comment;

public interface CommentService {
    Comment createComment(Comment comment);
    Comment getCommentBy(Integer commentId);
    Comment updateComment(Comment newComment);
    Comment deleteCommentBy(Integer commentId);
}
