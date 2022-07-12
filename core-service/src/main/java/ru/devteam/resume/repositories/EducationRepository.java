package ru.devteam.resume.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.devteam.resume.entities.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
}
