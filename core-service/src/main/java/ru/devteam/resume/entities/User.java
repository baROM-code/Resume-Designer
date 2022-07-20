package ru.devteam.resume.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "photo")
    private String photo;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private char gender;

    @Column(name = "dateofbirth")
    private LocalDateTime dateofbirth;

    @Column(name = "email")
    private String email;



//    @OneToMany
//    @JoinTable(name = "works",
//            joinColumns = @JoinColumn(name = "user_id"))
//    private List<Work> works;
//
//    @OneToMany
//    @JoinTable(name = "educations",
//            joinColumns = @JoinColumn(name = "user_id"))
//    private List<Education> educations;
//
//    @CreationTimestamp
//    @Column(name = "created_at")
//    private LocalDateTime created_at;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updated_at;

}
