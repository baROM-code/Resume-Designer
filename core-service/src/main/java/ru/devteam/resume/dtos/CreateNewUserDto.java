package ru.devteam.resume.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateNewUserDto {
    private String photo;
    private String firstname;
    private String lastname;
    private String password;
    private char gender;
    private LocalDateTime dateofbirth;
    private String email;


}
