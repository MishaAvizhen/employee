package com.avizhen.controller;

import com.avizhen.entity.Employee;
import com.avizhen.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeRestController {

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> all = employeeService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee employeeById = employeeService.findById(id);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> removeEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(" Employee was deleted ", HttpStatus.OK);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id,
                                             @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        return new ResponseEntity<>("Employee was updated", HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return new ResponseEntity<>("Employee was created", HttpStatus.OK);
    }
}
