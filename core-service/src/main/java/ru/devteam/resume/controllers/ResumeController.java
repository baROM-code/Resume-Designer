package ru.devteam.resume.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.converters.ResumeConverter;
import ru.devteam.resume.converters.UserConverter;
import ru.devteam.resume.dtos.CreateNewResumeDto;
import ru.devteam.resume.dtos.ResumeDto;
import ru.devteam.resume.dtos.WorkDto;
import ru.devteam.resume.entities.Resume;
import ru.devteam.resume.exceptions.ResourceNotFoundException;
import ru.devteam.resume.services.EducationService;
import ru.devteam.resume.services.ResumeService;
import ru.devteam.resume.services.UserService;
import ru.devteam.resume.services.WorkService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final ResumeConverter resumeConverter;
    private final UserService userService;
    private final UserConverter userConverter;
    private final EducationService educationService;
    private final WorkService workService;

    @GetMapping
    public List<Resume> getAllResumes() {
        return resumeService.findAll();
    }

    @GetMapping("user/{userId}")
    public List<Resume> getAllResumesByUserId(@PathVariable Long userId){
        return resumeService.findResumesByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResumeDto getResumeById(@PathVariable Long id) {
        ResumeDto resumeDto = resumeConverter.entityToDto(resumeService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Резюме с id: " + id + " не найдено")));
        Long userId = resumeDto.getUserId();
        resumeDto.setUserData(userConverter.entityToDto(userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Пользователь с id: " + userId + " не найден"))));
        resumeDto.setEducations(educationService.findAllEducationsByUseId(userId));
        resumeDto.setWorks(workService.findAllWorksByUseId(userId));
        return resumeDto;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewResume(@RequestBody CreateNewResumeDto createNewResumeDto) {
        resumeService.createNew(createNewResumeDto);
    }

    @PutMapping("/update/{id}")
    public void updateResume(@PathVariable Long id, @RequestBody ResumeDto resumeDto) {
        resumeDto.setId(id);
        resumeService.update(resumeConverter.dtoToEntity(resumeDto));
    }


}
