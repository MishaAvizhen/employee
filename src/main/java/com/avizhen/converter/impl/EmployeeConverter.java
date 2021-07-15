package com.avizhen.converter.impl;

import com.avizhen.converter.Converter;
import com.avizhen.dto.EmployeeDto;
import com.avizhen.entity.Department;
import com.avizhen.entity.Employee;
import com.avizhen.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter implements Converter<Employee, EmployeeDto> {


    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @Override
    public Employee convertToEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        return convertToExistingEntity(dto, employee);

    }

    @Override
    public Employee convertToExistingEntity(EmployeeDto dto, Employee entity) {
        Department department = departmentService.findById(dto.getDepartmentId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setJobTitle(dto.getJobTitle());
        entity.setGender(dto.getGender());
        entity.setDepartment(department);
        entity.setBirthDate(dto.getBirthDate());
        return entity;
    }
}
