package com.booleanuk.requests.controller;

import com.booleanuk.requests.model.Teacher;
import com.booleanuk.requests.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable int id) {
        Teacher teacherIsFound;
        teacherIsFound = this.teacherRepository.findById(id).orElse(null);
        if (teacherIsFound == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Teacher with this id was not found.");
        }
        return ResponseEntity.ok(teacherIsFound);
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(this.teacherRepository.save(teacher), HttpStatusCode.valueOf(201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        Teacher teacherToUpdate = this.getTeacher(id).getBody();
        if (teacherToUpdate != null) {
            teacherToUpdate.setName(teacher.getName());
            teacherToUpdate.setSubject(teacher.getSubject());
            teacherToUpdate.setLocation(teacher.getLocation());
            teacherToUpdate.setEmail(teacher.getEmail());
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Teacher with this id was not found.");
        }
        return new ResponseEntity<>(this.teacherRepository.save(teacherToUpdate), HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable int id) {
        Teacher teacherToDelete = this.getTeacher(id).getBody();
        if (teacherToDelete == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Teacher with this id was not found.");
        }
        this.teacherRepository.delete(teacherToDelete);
        return ResponseEntity.ok(teacherToDelete);
    }
}
