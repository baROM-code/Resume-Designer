package ru.devteam.resume.core.converters;

import org.springframework.stereotype.Component;
import ru.devteam.resume.core.dtos.EducationDto;
import ru.devteam.resume.core.entities.Education;

@Component
public class EducationConverter {
    public EducationDto entityToDto(Education education) {
        EducationDto educationDto = new EducationDto();
        educationDto.setId(education.getId());
        educationDto.setUserId(education.getUserId());
        educationDto.setOrganization(education.getOrganization());
        educationDto.setSpeciality(education.getSpeciality());
        educationDto.setYearStart(education.getYearStart());
        educationDto.setYearEnd(education.getYearEnd());
        return educationDto;
    }

    public Education dtoToEntity(EducationDto educationDto) {
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
