package ru.devteam.resume.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.devteam.resume.core.converters.ResumeConverter;
import ru.devteam.resume.core.converters.UserConverter;
import ru.devteam.resume.core.dtos.CreateNewResumeDto;
import ru.devteam.resume.core.dtos.ResumeFullDto;
import ru.devteam.resume.core.entities.Resume;
import ru.devteam.resume.core.exceptions.ResourceNotFoundException;
import ru.devteam.resume.core.repositories.ResumeRepository;
import ru.devteam.resume.core.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final ResumeConverter resumeConverter;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final EducationService educationService;
    private final WorkService workService;

    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    public List<Resume> findResumesByUserId(Long id) {
        return resumeRepository.findByUserId(id);
    }

    public Optional<Resume> findById(Long id) {
        return resumeRepository.findById(id);
    }

    public void createNew(CreateNewResumeDto createNewResumeDto) {
        Resume resume = new Resume();
        resume.setUserId(createNewResumeDto.getUserId());
        resume.setPost(createNewResumeDto.getPost());
        resume.setSalary(createNewResumeDto.getSalary());
        resume.setSchedule(createNewResumeDto.getSchedule());
        resume.setAboutMyself(createNewResumeDto.getAboutMyself());
        resumeRepository.save(resume);
    }

    public void update(Resume resume) {
        resumeRepository.save(resume);
    }

    @Transactional
    public ResumeFullDto getFullResumeById(Long id) {
        ResumeFullDto resumeFullDto = resumeConverter.entityToFullDto(resumeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Резюме с id: " + id + " не найдено")));
        Long userId = resumeFullDto.getUserId();
        resumeFullDto.setUserData(userConverter.entityToDto(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Пользователь с id: " + userId + " не найден"))));
        resumeFullDto.setEducations(educationService.findAllEducationsByUserId(userId));
        resumeFullDto.setWorks(workService.findAllWorksByUserId(userId));
        return  resumeFullDto;
    }
}
