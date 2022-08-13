package ru.devteam.resume.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.devteam.resume.enums.ScheduleType;

@Data
@NoArgsConstructor
public class ResumeShortDto {
    private Long id;
    private Long userId;
    private String post;
    private Long salary;
    private ScheduleType schedule;
    private String aboutMyself;
}
