package ru.devteam.resume.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.devteam.resume.enums.GenderType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateNewUserDto {
    private String photo;
    private String firstName;
    private String lastName;
    private String password;
    private GenderType gender;
    private LocalDateTime dateOfBirth;
    private String email;


}
