package ru.devteam.resume.converters;

import org.springframework.stereotype.Component;
import ru.devteam.resume.dtos.CreateNewEducationDto;
import ru.devteam.resume.dtos.EducationDto;
import ru.devteam.resume.entities.Education;

@Component
public class EducationConverter {
    public CreateNewEducationDto entityToDto(Education e) {
        CreateNewEducationDto createNewEducationDto = new CreateNewEducationDto();
//        createNewEducationDto.setUserId(e.getUserId());
        createNewEducationDto.setOrganization(e.getOrganization());
        createNewEducationDto.setSpeciality(e.getSpeciality());
        createNewEducationDto.setYearEnd(e.getYearEnd());
        return createNewEducationDto;
    }

    public Education dtoToEntity (EducationDto educationDto) {
        Education education = new Education();
        education.setId(educationDto.getId());
        education.setUserId(educationDto.getUserId());
        education.setOrganization(educationDto.getOrganization());
        education.setSpeciality(educationDto.getSpeciality());
        education.setYearStart(educationDto.getYearStart());
        education.setYearEnd(educationDto.getYearEnd());
        return education;
    }


}
