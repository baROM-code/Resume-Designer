package ru.devteam.resume.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.devteam.resume.entities.Resume;
import ru.devteam.resume.repositories.ResumeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public List<Resume> findAll(){
        return resumeRepository.findAll();

    }
}
