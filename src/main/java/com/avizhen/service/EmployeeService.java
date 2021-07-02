package com.avizhen.service;

import com.avizhen.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(Integer id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Integer id, Employee employee);

    void deleteEmployee(Integer id);

}
