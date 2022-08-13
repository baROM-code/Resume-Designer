package ru.devteam.resume.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.converters.UserConverter;
import ru.devteam.resume.dtos.CreateNewUserDto;
import ru.devteam.resume.dtos.UserDto;
import ru.devteam.resume.exceptions.ResourceNotFoundException;
import ru.devteam.resume.services.UserService;

@RestController
@RequiredArgsConstructor
@Tag(name="Пользователи", description="Управляет пользователями")
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @Operation(summary = "Получение данных пользователя")
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userConverter.entityToDto(userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с id:" + id + " не найден")));
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateNewUser(@RequestBody CreateNewUserDto createNewUserDto) {
        userService.createNewUser(createNewUserDto);
    }

}
