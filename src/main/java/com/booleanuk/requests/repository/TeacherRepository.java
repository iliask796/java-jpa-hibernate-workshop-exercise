package com.booleanuk.requests.repository;

import com.booleanuk.requests.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
