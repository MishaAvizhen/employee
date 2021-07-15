package com.avizhen.service;

import com.avizhen.dto.EmployeeDto;
import com.avizhen.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(Integer id);

    Employee createEmployee(EmployeeDto employeeDto);

    Employee updateEmployee(Integer id, EmployeeDto employeeDto);

    void deleteEmployee(Integer id);

}
