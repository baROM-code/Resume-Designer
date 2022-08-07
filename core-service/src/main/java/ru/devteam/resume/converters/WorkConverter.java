package ru.devteam.resume.converters;

import org.springframework.stereotype.Component;
import ru.devteam.resume.dtos.CreateNewWorkDto;
import ru.devteam.resume.dtos.WorkDto;
import ru.devteam.resume.entities.Work;

@Component
public class WorkConverter {
    public CreateNewWorkDto entityToDto(Work w) {
        CreateNewWorkDto createNewWorkDto = new CreateNewWorkDto();
//        createNewWorkDto.setUserId(w.getUserId());
        createNewWorkDto.setOrganization(w.getOrganization());
        createNewWorkDto.setPost(w.getPost());
        createNewWorkDto.setStartWork(w.getStartWork());
        createNewWorkDto.setEndWork(w.getEndWork());
        createNewWorkDto.setProgress(w.getProgress());
        return createNewWorkDto;
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
