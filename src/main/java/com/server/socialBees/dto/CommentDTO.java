package com.server.socialBees.dto;

import jakarta.validation.constraints.Size;
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
    private Long id;
    private Long userId;
    private Long workId;
    @Size(min = 64, max = 280, message = "Treść powinna mieć od 64 do 280 znaków!")
    private String content;
    private LocalDate date = LocalDate.now();
}
