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

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name="Образование", description="Управляет записями о Учебе")
@RequestMapping("/api/v1/educations")
public class EducationController {
    private final EducationService educationService;
    private final EducationConverter educationConverter;

    @Operation(summary = "Получение всех записей о учебе пользователя по его id")
    @GetMapping("/user/{userId}")
    public List<EducationDto> getAllEducationsByUserId(@PathVariable Long userId) {
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
