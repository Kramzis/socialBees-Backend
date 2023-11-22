package com.server.socialBees.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String birthday;
}
