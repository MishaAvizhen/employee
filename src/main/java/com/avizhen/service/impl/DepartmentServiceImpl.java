package com.avizhen.service.impl;

import com.avizhen.entity.Department;
import com.avizhen.exception.EmployeeServiceNotFoundException;
import com.avizhen.repository.DepartmentRepository;
import com.avizhen.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department findById(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        log.info("Department with id {} was found", id);
        return departmentOptional.orElseThrow(() -> new EmployeeServiceNotFoundException(id));
    }
}
