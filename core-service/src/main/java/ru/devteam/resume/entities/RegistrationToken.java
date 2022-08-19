package ru.devteam.resume.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registration_tokens")
@NoArgsConstructor
@Data
public class RegistrationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public RegistrationToken(String token, LocalDateTime expiredAt, User user) {
        this.token = token;
        this.expiredAt = expiredAt;
        this.user = user;
    }
}
