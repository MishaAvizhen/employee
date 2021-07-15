package com.avizhen.service.impl;

import com.avizhen.converter.impl.EmployeeConverter;
import com.avizhen.dto.EmployeeDto;
import com.avizhen.entity.Department;
import com.avizhen.entity.Employee;
import com.avizhen.exception.EmployeeServiceException;
import com.avizhen.exception.EmployeeServiceNotFoundException;
import com.avizhen.repository.EmployeeRepository;
import com.avizhen.service.DepartmentService;
import com.avizhen.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeConverter employeeConverter;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeConverter employeeConverter,
                               DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
        this.departmentService = departmentService;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElseThrow(() -> new EmployeeServiceNotFoundException(id));
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        CheckIfDepartmentExist(employeeDto);
        Employee employee = employeeConverter.convertToEntity(employeeDto);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employeeToUpdate = this.findById(id);
        CheckIfDepartmentExist(employeeDto);
        Employee employee = employeeConverter.convertToExistingEntity(employeeDto, employeeToUpdate);
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = this.findById(id);
        employeeRepository.delete(employee);
    }

    private Department CheckIfDepartmentExist(EmployeeDto employeeDto) {
        return departmentService.findById(employeeDto.getDepartmentId());
    }
}
