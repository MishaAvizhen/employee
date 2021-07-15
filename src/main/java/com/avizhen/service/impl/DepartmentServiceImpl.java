package com.avizhen.service.impl;

import com.avizhen.entity.Department;
import com.avizhen.repository.DepartmentRepository;
import com.avizhen.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public Department findById(Integer id) {
        return departmentRepository.getOne(id);
    }
}
