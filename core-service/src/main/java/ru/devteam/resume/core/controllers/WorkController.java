package ru.devteam.resume.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.core.converters.WorkConverter;
import ru.devteam.resume.core.dtos.CreateNewWorkDto;
import ru.devteam.resume.core.dtos.WorkDto;
import ru.devteam.resume.core.services.UserService;
import ru.devteam.resume.core.services.WorkService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name="Работа", description="Управляет записями о Работе")
@RequestMapping("/api/v1/works")
public class WorkController {
    private final WorkService workService;
    private final WorkConverter workConverter;
    private final UserService userService;

    @Operation(summary = "Получение всех записей о работе текущего пользователя")
    @GetMapping("/user")
    public List<WorkDto> getAllWorksByUserId(Principal principal) {
        if (principal == null) {return null;}
        Long userId = userService.findByEmail(principal.getName()).get().getId();
        return workService.findAllWorksByUserId(userId);
    }

    @Operation(summary = "Создание записи о работе")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewWork(@RequestBody CreateNewWorkDto createNewWorkDto) {
        workService.createNewWork(createNewWorkDto);
    }

    @Operation(summary = "Обновление записи о работе")
    @PutMapping()
    public void updateWork(@RequestBody WorkDto workDto) {
        workService.update(workConverter.dtoToEntity(workDto));
    }

    @Operation(summary = "Удаление записи о работе")
    @DeleteMapping("/{id}")
    public void deleteWork(@PathVariable Long id) {
        workService.delete(id);
    }


}
