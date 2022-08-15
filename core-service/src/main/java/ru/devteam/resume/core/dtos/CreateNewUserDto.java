package ru.devteam.resume.core.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.devteam.resume.core.enums.GenderType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateNewUserDto {
    private String photo;
    private String firstName;
    private String lastName;
    private String password;
    private GenderType gender;
    private LocalDate dateOfBirth;
    private String email;
}
