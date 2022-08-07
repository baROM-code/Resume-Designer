package ru.devteam.resume.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.devteam.resume.dtos.CreateNewWorkDto;
import ru.devteam.resume.entities.Work;
import ru.devteam.resume.repositories.WorkRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;

    public List<Work> findAll() {
        return workRepository.findAll();
    }

    public void createNewWork(CreateNewWorkDto workDto) {
        Work work = new Work();
//        work.setUserId(educationDto.getUserId());
        work.setOrganization(workDto.getOrganization());
        work.setPost(workDto.getPost());
        work.setStartWork(workDto.getStartWork());
        work.setEndWork(workDto.getEndWork());
        work.setProgress(workDto.getProgress());
//        work.setCreatedAt(workDto.getCreatedAt());
//        work.setUpdatedAt(workDto.getUpdatedAt());
        workRepository.save(work);
    }

    public void update(Work work) {
        workRepository.save(work);
    }

    public void add(Work work) {
        workRepository.save(work);
    }

    public List<Work> findAllWorksByUseId(Long userId) {
        return workRepository.findByUserId(userId);
    }
}
