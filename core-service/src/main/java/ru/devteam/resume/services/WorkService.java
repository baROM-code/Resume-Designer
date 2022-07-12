package ru.devteam.resume.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.devteam.resume.entities.Work;
import ru.devteam.resume.repositories.WorkRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;

    public List<Work> findAll(){
        return workRepository.findAll();

    }
}
