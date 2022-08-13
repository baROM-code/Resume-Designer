package ru.devteam.resume.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.converters.WorkConverter;
import ru.devteam.resume.dtos.CreateNewWorkDto;
import ru.devteam.resume.dtos.WorkDto;
import ru.devteam.resume.services.WorkService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/works")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService workService;
    private final WorkConverter workConverter;

    @GetMapping("/user/{userId}")
    public List<WorkDto> getAllWorksUserId(@PathVariable Long userId) {
        return workService.findAllWorksByUseId(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewWork(@RequestBody CreateNewWorkDto createNewWorkDto) {
        workService.createNewWork(createNewWorkDto);
    }

    @PutMapping("/update")
    public void updateWork(@RequestBody WorkDto workDto) {
        workService.update(workConverter.dtoToEntity(workDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWork(@PathVariable Long id) {
        workService.delete(id);
    }


}
