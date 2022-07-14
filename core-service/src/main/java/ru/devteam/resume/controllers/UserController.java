package ru.devteam.resume.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.devteam.resume.converters.UserConverter;
import ru.devteam.resume.dtos.CreateNewUserDto;
import ru.devteam.resume.dtos.UserDto;
import ru.devteam.resume.entities.User;
import ru.devteam.resume.exceptions.ResourceNotFoundException;
import ru.devteam.resume.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userConverter.entityToDto(userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с id:" + id + " не найден")));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateNewUser(@RequestBody CreateNewUserDto createNewUserDto) {
        userService.createNewUser(createNewUserDto);
    }

}
