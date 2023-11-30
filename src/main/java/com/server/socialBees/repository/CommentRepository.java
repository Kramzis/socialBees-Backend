package com.server.socialBees.repository;

import com.server.socialBees.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findCommentById(Long commentId);
}
