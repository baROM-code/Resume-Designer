package ru.devteam.resume.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.devteam.resume.entities.Education;
import ru.devteam.resume.entities.Work;
import ru.devteam.resume.enums.GenderType;
import ru.devteam.resume.enums.ScheduleType;
import java.time.LocalDate;
import java.util.List;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResumeDto {

    private Long id;
    private Long userId;

    private String photo;
    private String firstName;
    private String lastName;
    private GenderType gender;
    private LocalDate dateOfBirth;
    private String email;

    private String post;
    private Long salary;
    private ScheduleType schedule;
    private String aboutMyself;
    private List<Work> works;
    private List<Education> educations;

    public ResumeDto setUserData (UserDto userDto) {
        this.setPhoto(userDto.getPhoto());
        this.setFirstName(userDto.getFirstName());
        this.setLastName(userDto.getLastName());
        this.setGender(userDto.getGender());
        this.setDateOfBirth(userDto.getDateOfBirth());
        this.setEmail(userDto.getEmail());
        return this;
    }

}
