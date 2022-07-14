package ru.devteam.resume.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.devteam.resume.entities.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
