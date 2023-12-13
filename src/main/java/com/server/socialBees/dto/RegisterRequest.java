package com.server.socialBees.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Long id;
    @Size(min = 3, max = 16, message = "Username powinien mieć od 3 do 16 znaków!")
    private String username;
    @Email
    private String email;
    @Size(min = 8, max = 20, message = "Hasło powinno mieć od 8 do 20 znaków!")
    private String password;
    @Size(min = 3, max = 32, message = "Imię powinno mieć od 3 do 32 znaków!")
    private String name;
    @Size(min = 3, max = 32, message = "Nazwisko powino mieć od 3 do 32 znaków!")
    private String surname;
    @NotBlank
    private String birthday;
}
