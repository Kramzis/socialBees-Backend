package com.server.socialBees.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Integer userId;
    private Integer workId;
    private Integer commentId;
    private String content;
    private LocalDate date;
}
