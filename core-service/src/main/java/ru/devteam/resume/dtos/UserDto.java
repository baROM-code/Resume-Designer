package ru.devteam.resume.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String photo;
    private String firstname;
    private String lastname;
    private String password;
    private char gender;
    private LocalDateTime dateofbirth;
    private String email;
}
