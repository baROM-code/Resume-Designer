package ru.devteam.resume.core.converters;

import org.springframework.stereotype.Component;
import ru.devteam.resume.core.dtos.WorkDto;
import ru.devteam.resume.core.entities.Work;

@Component
public class WorkConverter {
    public WorkDto entityToDto(Work work) {
        WorkDto workDto = new WorkDto();
        workDto.setId(work.getId());
        workDto.setUserId(work.getUserId());
        workDto.setOrganization(work.getOrganization());
        workDto.setPost(work.getPost());
        workDto.setStartWork(work.getStartWork());
        workDto.setEndWork(work.getEndWork());
        workDto.setProgress(work.getProgress());
        return workDto;
    }

    public Work dtoToEntity (WorkDto workDto) {
        Work work = new Work();
        work.setId(workDto.getId());
        work.setUserId(workDto.getUserId());
        work.setOrganization(workDto.getOrganization());
        work.setPost(workDto.getPost());
        work.setStartWork(workDto.getStartWork());
        work.setEndWork(workDto.getEndWork());
        work.setProgress(workDto.getProgress());
        return work;
    }

}
