package com.avizhen.service;

import com.avizhen.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findEmployeeById(Integer id);

    void createEmployee(Employee employee);
    void updateEmployee(Integer id, Employee employee);
    void deleteEmployee(Integer id);

}
