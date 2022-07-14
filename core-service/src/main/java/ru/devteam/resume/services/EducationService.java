package ru.devteam.resume.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.devteam.resume.entities.Education;
import ru.devteam.resume.repositories.EducationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;

    public List<Education> findAll(){
        return educationRepository.findAll();

    }
}
