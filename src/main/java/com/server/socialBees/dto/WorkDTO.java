package com.server.socialBees.dto;

import jakarta.validation.constraints.Size;
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
    @Size(min = 12, max = 64, message = "Tytuł powinien mieć od 12 do 64 znaków!")
    private String title;
    @Size(min = 64, max = 280, message = "Treść powinna mieć od 64 do 280 znaków!")
    private String content;
    private LocalDate date = LocalDate.now();
    private List<String> tags;
    private MultipartFile file;
}
