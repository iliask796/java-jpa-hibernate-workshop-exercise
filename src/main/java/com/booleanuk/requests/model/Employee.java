package com.booleanuk.requests.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "location")
    private String location;
    @Column(name = "email_address")
    private String email;

    public Employee(String firstName, String lastName, String location, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.email = email;
    }
}
