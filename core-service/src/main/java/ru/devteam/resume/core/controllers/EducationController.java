package ru.devteam.resume.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.core.converters.EducationConverter;
import ru.devteam.resume.core.dtos.CreateNewEducationDto;
import ru.devteam.resume.core.dtos.EducationDto;
import ru.devteam.resume.core.services.EducationService;
import ru.devteam.resume.core.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name="Образование", description="Управляет записями о Учебе")
@RequestMapping("/api/v1/educations")
public class EducationController {
    private final EducationService educationService;
    private final EducationConverter educationConverter;
    private final UserService userService;

    @Operation(summary = "Получение всех записей о учебе текущего пользователя")
    @GetMapping("/user")
    public List<EducationDto> getAllEducationsByUserId(Principal principal) {
        if (principal == null) {return null;}
        Long userId = userService.findByEmail(principal.getName()).get().getId();
        return educationService.findAllEducationsByUserId(userId);
    }

    @Operation(summary = "Создание записи о учебе")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewEducation(@RequestBody CreateNewEducationDto createNewEducationDto) {
        educationService.createNewEducation(createNewEducationDto);
    }

    @Operation(summary = "Обновление записи о учебе")
    @PutMapping()
    public void updateEducation(@RequestBody EducationDto educationDto) {
        educationService.update(educationConverter.dtoToEntity(educationDto));
    }

    @Operation(summary = "Удаление записи о учебе")
    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id) {
        educationService.delete(id);
    }

}
