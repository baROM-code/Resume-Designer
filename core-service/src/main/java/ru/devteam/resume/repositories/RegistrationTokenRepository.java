package ru.devteam.resume.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.devteam.resume.entities.RegistrationToken;

@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {

}
