package ru.devteam.resume.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.converters.ResumeConverter;
import ru.devteam.resume.dtos.CreateNewResumeDto;
import ru.devteam.resume.dtos.ResumeFullDto;
import ru.devteam.resume.dtos.ResumeShortDto;
import ru.devteam.resume.services.ResumeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final ResumeConverter resumeConverter;

    @GetMapping
    public List<ResumeShortDto> getAllResumes() {
        return resumeService.findAll().stream().map(resumeConverter::entityToShortDto).collect(Collectors.toList());
    }

    @GetMapping("user/{userId}")
    public List<ResumeShortDto> getAllResumesByUserId(@PathVariable Long userId){
        return resumeService.findResumesByUserId(userId).stream().map(resumeConverter::entityToShortDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResumeFullDto getResumeById(@PathVariable Long id) {
        return resumeService.getFullResumeById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewResume(@RequestBody CreateNewResumeDto createNewResumeDto) {
        resumeService.createNew(createNewResumeDto);
    }

    @PutMapping("/update")
    public void updateResume(@RequestBody ResumeShortDto resumeShortDto) {
        resumeService.update(resumeConverter.shortDtoToEntity(resumeShortDto));
    }

}
