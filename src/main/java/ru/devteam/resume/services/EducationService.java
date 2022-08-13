package ru.devteam.resume.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.devteam.resume.converters.EducationConverter;
import ru.devteam.resume.dtos.CreateNewEducationDto;
import ru.devteam.resume.dtos.EducationDto;
import ru.devteam.resume.entities.Education;
import ru.devteam.resume.repositories.EducationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;
    private final EducationConverter educationConverter;

    public void createNewEducation(CreateNewEducationDto educationDto) {
        Education education = new Education();
        education.setUserId(educationDto.getUserId());
        education.setOrganization(educationDto.getOrganization());
        education.setSpeciality(educationDto.getSpeciality());
        education.setYearStart(educationDto.getYearStart());
        education.setYearEnd(educationDto.getYearEnd());
        educationRepository.save(education);
    }

    public List<EducationDto> findAllEducationsByUseId(Long userId) {
        return educationRepository.findByUserId(userId).stream().map(educationConverter::entityToDto).collect(Collectors.toList());
    }

    public void update(Education education) {
        educationRepository.save(education);
    }

    public void delete(Long id) {
        educationRepository.deleteById(id);
    }
}
