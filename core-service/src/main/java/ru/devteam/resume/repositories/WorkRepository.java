package ru.devteam.resume.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.devteam.resume.entities.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
}
