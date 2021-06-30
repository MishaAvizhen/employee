package com.avizhen.dao;

import com.avizhen.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee getEmployeeById(Integer id);

    List<Employee> getAllEmployees();

    void deleteEmployee(Integer id);

    void updateEmployee(Integer id, Employee employee);

    void createEmployee(Employee employee);

    Integer generateUniqueKey();
}
