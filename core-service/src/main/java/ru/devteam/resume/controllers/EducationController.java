package ru.devteam.resume.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.converters.EducationConverter;
import ru.devteam.resume.dtos.CreateNewEducationDto;
import ru.devteam.resume.dtos.CreateNewResumeDto;
import ru.devteam.resume.dtos.EducationDto;
import ru.devteam.resume.dtos.WorkDto;
import ru.devteam.resume.entities.Education;
import ru.devteam.resume.entities.Work;
import ru.devteam.resume.services.EducationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/educations")
@RequiredArgsConstructor
public class EducationController {
    private final EducationService educationService;
    private final EducationConverter educationConverter;

    @GetMapping
    public List<Education> getAllEducations() {
        return educationService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Education> getAllEducationsUserId(@PathVariable Long userId){
        return educationService.findAllEducationsByUseId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewEducation(@RequestBody CreateNewEducationDto createNewEducationDto) {
        educationService.createNewEducation(createNewEducationDto);
    }

    @PutMapping("/update/{id}")
    public void updateEducation(@PathVariable Long id, @RequestBody EducationDto educationDto) {
        educationDto.setId(id);
        educationService.update(educationConverter.dtoToEntity(educationDto));
    }


}
