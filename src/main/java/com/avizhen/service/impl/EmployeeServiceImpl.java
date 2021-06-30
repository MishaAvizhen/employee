package com.avizhen.service.impl;

import com.avizhen.dao.DepartmentDao;
import com.avizhen.dao.EmployeeDao;
import com.avizhen.entity.Department;
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
    public Employee findEmployeeById(Integer id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public void createEmployee(Employee employee) {
         employeeDao.createEmployee(employee);
    }

    @Override
    public void updateEmployee(Integer id, Employee employee) {
        employeeDao.updateEmployee(id,employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeDao.deleteEmployee(id);

    }
}
