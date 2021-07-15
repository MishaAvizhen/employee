package com.avizhen.service.impl;

import com.avizhen.converter.impl.EmployeeConverter;
import com.avizhen.dto.EmployeeDto;
import com.avizhen.entity.Employee;
import com.avizhen.repository.EmployeeRepository;
import com.avizhen.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeConverter employeeConverter;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeConverter employeeConverter) {
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    @Override
    public Employee findById(Integer id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeConverter.convertToEntity(employeeDto);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employeeToUpdate = this.findById(id);
        Employee employee = employeeConverter.convertToExistingEntity(employeeDto, employeeToUpdate);
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);

    }
}
