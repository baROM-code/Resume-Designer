package ru.devteam.resume.converters;

import org.springframework.stereotype.Component;
import ru.devteam.resume.dtos.UserDto;
import ru.devteam.resume.entities.User;

import java.util.List;

@Component
public class UserConverter {
    public UserDto entityToDto(User u) {
        UserDto userDto = new UserDto();
        userDto.setId(u.getId());
        userDto.setFirstName(u.getFirstName());
        userDto.setLastName(u.getLastName());
        userDto.setEmail(u.getEmail());
        userDto.setGender(u.getGender());
        userDto.setDateOfBirth(u.getDateOfBirth());
        userDto.setPhoto(u.getPhoto());
        return userDto;
    }

    public List<UserDto> findAll() {
        return null;
    }
}
