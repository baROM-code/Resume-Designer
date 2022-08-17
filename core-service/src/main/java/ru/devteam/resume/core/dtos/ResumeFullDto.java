package ru.devteam.resume.core.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.devteam.resume.core.enums.GenderType;
import ru.devteam.resume.core.enums.ScheduleType;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ResumeFullDto {

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
    private List<WorkDto> works;
    private List<EducationDto> educations;

    public ResumeFullDto setUserData (UserDto userDto) {
        this.setPhoto(userDto.getPhoto());
        this.setFirstName(userDto.getFirstName());
        this.setLastName(userDto.getLastName());
        this.setGender(userDto.getGender());
        this.setDateOfBirth(userDto.getDateOfBirth());
        this.setEmail(userDto.getEmail());
        return this;
    }

}
