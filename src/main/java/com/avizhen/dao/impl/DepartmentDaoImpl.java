package com.avizhen.dao.impl;

import com.avizhen.dao.DepartmentDao;
import com.avizhen.dao.mapper.DepartmentMapper;
import com.avizhen.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DepartmentDaoImpl implements DepartmentDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Department findDepartmentById(Integer id) {
        return jdbcTemplate.query("SELECT * FROM department WHERE id=?", new Object[]{id}, new DepartmentMapper())
                .stream().findAny().orElse(null);
    }
}
