package ru.devteam.resume.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.converters.WorkConverter;
import ru.devteam.resume.dtos.CreateNewEducationDto;
import ru.devteam.resume.dtos.CreateNewWorkDto;
import ru.devteam.resume.dtos.WorkDto;
import ru.devteam.resume.entities.Work;
import ru.devteam.resume.services.WorkService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/works")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService workService;
    private final WorkConverter workConverter;

    @GetMapping
    public List<Work> getAllWorks() {
        return workService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Work> getAllWorksUserId(@PathVariable Long userId){
        return workService.findAllWorksByUseId(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewWork(@RequestParam(required = false) Long userId, @RequestBody WorkDto workDto) {
        if (userId > 0) {
            workDto.setUserId(userId);
        }
        workService.add(workConverter.dtoToEntity(workDto));
        //workService.createNewWork(createNewWorkDto);
    }

    @PutMapping("/update/{id}")
    public void updateWork(@PathVariable Long id, @RequestBody WorkDto workDto) {
        workDto.setId(id);
        workService.update(workConverter.dtoToEntity(workDto));
    }

}
