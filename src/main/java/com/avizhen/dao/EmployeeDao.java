package com.avizhen.dao;

import com.avizhen.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee getById(Integer id);

    List<Employee> getAllEmployees();

    void deleteEmployee(Integer id);

    Employee updateEmployee(Integer id, Employee employee);

    Employee createEmployee(Employee employee);

}
