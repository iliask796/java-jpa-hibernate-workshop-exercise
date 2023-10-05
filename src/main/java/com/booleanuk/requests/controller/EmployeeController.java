package com.booleanuk.requests.controller;

import com.booleanuk.requests.model.Employee;
import com.booleanuk.requests.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        Employee employee;
        employee = this.employeeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Employee with this id was not found.")
        );
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(this.employeeRepository.save(employee),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        Employee employeeToUpdate = getEmployee(id).getBody();
        if (employeeToUpdate != null){
            employeeToUpdate.setFirstName(employee.getFirstName());
            employeeToUpdate.setLastName(employee.getLastName());
            employeeToUpdate.setLocation(employee.getLocation());
            employeeToUpdate.setEmail(employee.getEmail());
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Employee with this id was not found.");
        }
        return new ResponseEntity<>(this.employeeRepository.save(employeeToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable int id){
        Employee employeeToDelete = this.getEmployee(id).getBody();
        if (employeeToDelete == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Employee with this id was not found.");
        }
        this.employeeRepository.delete(employeeToDelete);
        return ResponseEntity.ok(employeeToDelete);
    }
}
