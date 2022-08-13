package ru.devteam.resume.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.devteam.resume.enums.ScheduleType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateNewResumeDto {
    private Long userId;
    private String post;
    private Long salary;
    private ScheduleType schedule;
    private String aboutMyself;
}
