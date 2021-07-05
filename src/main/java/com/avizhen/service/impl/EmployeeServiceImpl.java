package com.avizhen.service.impl;

import com.avizhen.dao.EmployeeDao;
import com.avizhen.entity.Employee;
import com.avizhen.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.getAllEmployees();
    }


    @Override
    public Employee findById(Integer id) {
        return employeeDao.getById(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeDao.createEmployee(employee);
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        return employeeDao.updateEmployee(id, employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeDao.deleteEmployee(id);

    }
}
