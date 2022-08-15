package ru.devteam.resume.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.devteam.resume.core.entities.Work;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findByUserId(Long userId);
}
