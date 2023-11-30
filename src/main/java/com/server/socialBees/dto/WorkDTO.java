package com.server.socialBees.dto;

import com.server.socialBees.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkDTO {
    private Long userId;
    private String title;
    private String content;
    private LocalDate date;
    private List<String> tags;
    private List<MultipartFile> files;
}
