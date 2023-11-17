package com.server.socialBees.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkDTO {
    private Integer userId;
    private String title;
    private String content;
    private LocalDate date;
    private List<String> tags;
}
