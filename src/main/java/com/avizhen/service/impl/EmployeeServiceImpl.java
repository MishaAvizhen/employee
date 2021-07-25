package com.avizhen.service.impl;

import com.avizhen.converter.impl.EmployeeConverter;
import com.avizhen.dto.EmployeeDto;
import com.avizhen.entity.Department;
import com.avizhen.entity.Employee;
import com.avizhen.exception.EmployeeServiceNotFoundException;
import com.avizhen.repository.EmployeeRepository;
import com.avizhen.service.DepartmentService;
import com.avizhen.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
        List<Employee> employeeList = employeeRepository.findAll();
        log.info("Employees list size:{}", employeeList.size());
        return employeeList;
    }

    @Override
    public Employee findById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        log.info("Employee {}  was found", employeeOptional);
        log.warn("warning level");
        log.error("error level");
        log.debug("debug level");
        return employeeOptional.orElseThrow(() -> new EmployeeServiceNotFoundException(id));
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        CheckIfDepartmentExist(employeeDto);
        Employee employee = employeeConverter.convertToEntity(employeeDto);
        log.info("Employee with info {}  was created", employee);
        return employeeRepository.save(employee);
    }
    @Transactional
    @Override
    public Employee updateEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employeeToUpdate = this.findById(id);
        CheckIfDepartmentExist(employeeDto);
        Employee employee = employeeConverter.convertToExistingEntity(employeeDto, employeeToUpdate);
        log.info("Employee with info {}  was updated ", employee);
        return employeeRepository.saveAndFlush(employee);
    }
    @Transactional
    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = this.findById(id);
        log.info("Employee with info {}  was deleted", employee);
        employeeRepository.delete(employee);
    }

    @Override
    public Employee findByFirstNameAndLastName(String firstName, String lastName) {
        Employee employee = employeeRepository.findByName1AndName2(firstName, lastName);
        if (employee == null) {
            throw new EmployeeServiceNotFoundException("Employee not found");
        }
        log.info("Employee {}  was found", employee);
        return employee;
    }

    private Department CheckIfDepartmentExist(EmployeeDto employeeDto) {
        return departmentService.findById(employeeDto.getDepartmentId());
    }
}
