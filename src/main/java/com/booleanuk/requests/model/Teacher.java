package com.booleanuk.requests.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "subject")
    private String subject;
    @Column(name = "email")
    private String email;

    public Teacher(String name, String location, String subject, String email) {
        this.name = name;
        this.location = location;
        this.subject = subject;
        this.email = email;
    }
}
