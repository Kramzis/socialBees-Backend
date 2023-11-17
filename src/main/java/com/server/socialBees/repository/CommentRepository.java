package com.server.socialBees.repository;

import com.server.socialBees.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findCommentById(Integer commentId);
}
