package ru.devteam.resume.core.converters;

import org.springframework.stereotype.Component;
import ru.devteam.resume.core.dtos.ResumeFullDto;
import ru.devteam.resume.core.dtos.ResumeShortDto;
import ru.devteam.resume.core.entities.Resume;

@Component
public class ResumeConverter {
    public ResumeShortDto entityToShortDto(Resume resume) {
        ResumeShortDto resumeDto = new ResumeShortDto();
        resumeDto.setId(resume.getId());
        resumeDto.setUserId(resume.getUserId());
        resumeDto.setPost(resume.getPost());
        resumeDto.setSalary(resume.getSalary());
        resumeDto.setSchedule(resume.getSchedule());
        resumeDto.setAboutMyself(resume.getAboutMyself());
        return resumeDto;
    }

    public Resume shortDtoToEntity (ResumeShortDto resumeDto) {
        Resume resume = new Resume();
        resume.setId(resumeDto.getId());
        resume.setUserId(resumeDto.getUserId());
        resume.setPost(resumeDto.getPost());
        resume.setSalary(resumeDto.getSalary());
        resume.setSchedule(resumeDto.getSchedule());
        resume.setAboutMyself(resumeDto.getAboutMyself());
        return resume;
    }

    public ResumeFullDto entityToFullDto(Resume resume) {
        ResumeFullDto resumeDto = new ResumeFullDto();
        resumeDto.setId(resume.getId());
        resumeDto.setUserId(resume.getUserId());
        resumeDto.setPost(resume.getPost());
        resumeDto.setSalary(resume.getSalary());
        resumeDto.setSchedule(resume.getSchedule());
        resumeDto.setAboutMyself(resume.getAboutMyself());
        return resumeDto;
    }
}
