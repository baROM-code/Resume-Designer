package ru.devteam.resume.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.devteam.resume.dtos.CreateNewResumeDto;
import ru.devteam.resume.entities.Resume;
import ru.devteam.resume.repositories.ResumeRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    public List<Resume> findResumesByUserId(Long id) {
        return resumeRepository.findByUserId(id);
    }

    public Optional<Resume> findById(Long id) {
        return resumeRepository.findById(id);
    }

    public void createNew (CreateNewResumeDto createNewResumeDto){
        Resume resume = new Resume();
        resume.setUserId(createNewResumeDto.getUserId());
        resume.setPost(createNewResumeDto.getPost());
        resume.setSalary(createNewResumeDto.getSalary());
        resume.setSchedule(createNewResumeDto.getSchedule());
        resume.setAboutMyself(createNewResumeDto.getAboutMyself());
        resumeRepository.save(resume);
    }

    public void update (Resume resume) {
        resumeRepository.save(resume);
    }
}
