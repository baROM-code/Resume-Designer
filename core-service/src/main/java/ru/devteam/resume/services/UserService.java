package ru.devteam.resume.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.devteam.resume.dtos.CreateNewUserDto;
import ru.devteam.resume.entities.User;
import ru.devteam.resume.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteById(Long id){userRepository.deleteById(id);}

    public void createNewUser(CreateNewUserDto createNewUserDto) {
        User user = new User();
        user.setPhoto(createNewUserDto.getPhoto());
        user.setFirstname(createNewUserDto.getFirstname());
        user.setLastname(createNewUserDto.getLastname());
        user.setGender(createNewUserDto.getGender());
        user.setEmail(createNewUserDto.getEmail());
        user.setPassword(createNewUserDto.getPassword());
        userRepository.save(user);
    }
}
