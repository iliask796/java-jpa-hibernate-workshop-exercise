package com.booleanuk.requests.repository;

import com.booleanuk.requests.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
