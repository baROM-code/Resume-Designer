package ru.devteam.resume.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.devteam.resume.converters.ResumeConverter;
import ru.devteam.resume.converters.UserConverter;
import ru.devteam.resume.dtos.CreateNewResumeDto;
import ru.devteam.resume.dtos.ResumeFullDto;
import ru.devteam.resume.entities.Resume;
import ru.devteam.resume.exceptions.ResourceNotFoundException;
import ru.devteam.resume.repositories.ResumeRepository;
import ru.devteam.resume.repositories.UserRepository;

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

    public ResumeFullDto getFullResumeById(Long id) {
        ResumeFullDto resumeFullDto = resumeConverter.entityToFullDto(resumeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Резюме с id: " + id + " не найдено")));
        Long userId = resumeFullDto.getUserId();
        resumeFullDto.setUserData(userConverter.entityToDto(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Пользователь с id: " + userId + " не найден"))));
        resumeFullDto.setEducations(educationService.findAllEducationsByUseId(userId));
        resumeFullDto.setWorks(workService.findAllWorksByUseId(userId));
        return  resumeFullDto;
    }
}
