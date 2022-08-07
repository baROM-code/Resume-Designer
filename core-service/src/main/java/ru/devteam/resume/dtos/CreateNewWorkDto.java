package ru.devteam.resume.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateNewWorkDto {
    private String organization;
    private String post;
    private LocalDate startWork;
    private LocalDate endWork;
    private String progress;
}
