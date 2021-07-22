package com.avizhen.controller;

import com.avizhen.dto.EmployeeDto;
import com.avizhen.entity.Employee;
import com.avizhen.service.EmployeeService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/employees")
@Api(tags = " Employee controller", description = " Operations with employee ")
public class EmployeeRestController {

    private EmployeeService employeeService;


    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> all = employeeService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee employeeById = employeeService.findById(id);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(" Employee was deleted ", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id,
                                                 @RequestBody @Valid EmployeeDto employeeDto) {

        employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>("Employee was updated", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody @Valid  EmployeeDto employeeDto) {
        employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>("Employee was created", HttpStatus.OK);
    }
}
