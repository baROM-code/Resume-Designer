package ru.devteam.resume.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.devteam.resume.core.converters.WorkConverter;
import ru.devteam.resume.core.dtos.CreateNewWorkDto;
import ru.devteam.resume.core.dtos.WorkDto;
import ru.devteam.resume.core.entities.Work;
import ru.devteam.resume.core.repositories.WorkRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;
    private final WorkConverter workConverter;

    public void createNewWork(CreateNewWorkDto createNewWorkDto) {
        Work work = new Work();
        work.setUserId(createNewWorkDto.getUserId());
        work.setOrganization(createNewWorkDto.getOrganization());
        work.setPost(createNewWorkDto.getPost());
        work.setStartWork(createNewWorkDto.getStartWork());
        work.setEndWork(createNewWorkDto.getEndWork());
        work.setProgress(createNewWorkDto.getProgress());
        workRepository.save(work);
    }

    public void update(Work work) {
        workRepository.save(work);
    }

    public List<WorkDto> findAllWorksByUseId(Long userId) {
        return workRepository.findByUserId(userId).stream().map(workConverter::entityToDto).collect(Collectors.toList());
    }

    public void delete(Long id) {
        workRepository.deleteById(id);
    }
}
