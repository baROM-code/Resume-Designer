package ru.devteam.resume.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateNewEducationDto {
    private Long userId;
    private String organization;
    private String speciality;
    private LocalDate yearStart;
    private LocalDate yearEnd;
}
