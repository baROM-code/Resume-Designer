package ru.devteam.resume.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "post")
    private String post;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "schedule")
    private String schedule; // График работы (Полный день, Гибкий график, Удаленная работа, Подработка)

    @Column(name = "about_myself")
    private String about_myself;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
