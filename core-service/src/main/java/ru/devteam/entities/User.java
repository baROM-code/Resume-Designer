package ru.devteam.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String photo;
    private String userfirstname;
    private String userlastname;
    private String password;
    private char gender;
    private LocalDateTime dateofbirth;
    private String email;

    @OneToMany
    @JoinTable(name = "works",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Work> works;

    @OneToMany
    @JoinTable(name = "educations",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Education> educations;

    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

}
