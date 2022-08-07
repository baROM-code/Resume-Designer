package ru.devteam.resume.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.devteam.resume.enums.ScheduleType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resumes")
@NoArgsConstructor
@Data
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post")
    private String post;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "schedule")
    @Enumerated(EnumType.STRING)
    private ScheduleType schedule;

    @Column(name = "about_myself")
    private String aboutMyself;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
