package com.avizhen.dao.impl;

import com.avizhen.dao.DepartmentDao;
import com.avizhen.dao.EmployeeDao;
import com.avizhen.dao.mapper.EmployeeMapper;
import com.avizhen.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
    private JdbcTemplate jdbcTemplate;
    private DepartmentDao departmentDao;


    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate, DepartmentDao departmentDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.departmentDao = departmentDao;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return jdbcTemplate.query("SELECT e.* FROM employee AS e LEFT JOIN department AS d ON e.department_id = d.id WHERE e.id=?",
                new Object[]{id}, new EmployeeMapper(departmentDao))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT e.* FROM employee AS e LEFT JOIN department AS d ON e.department_id = d.id",
                new EmployeeMapper(departmentDao));

    }

    @Override
    public void deleteEmployee(Integer id) {
        jdbcTemplate.update("DELETE FROM employee WHERE id=?", id);

    }

    @Override
    public void updateEmployee(Integer id,Employee employee) {
        jdbcTemplate.update("UPDATE employee SET first_name=?, last_name=?, gender=?,job_title=?,date_of_birth=?,department_id=?  WHERE id=?",
                employee.getFirstName(), employee.getLastName(), employee.getGender().name(), employee.getJobTitle(),
                employee.getBirthDate(), employee.getDepartment().getId(),id);
    }

    @Override
    public void createEmployee(Employee employee) {
        jdbcTemplate.update("INSERT INTO employee VALUES(null, ?, ?, ?,?, ?, ?)", employee.getFirstName(),
                employee.getLastName(), employee.getDepartment().getId(), employee.getJobTitle(),
                employee.getGender().name(), employee.getBirthDate());
    }

    @Override
    public Integer generateUniqueKey() {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String getIdSql = "SELECT e.id FROM employee";

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        return connection.prepareStatement(getIdSql, new String[] {"id"});
                    }
                }, keyHolder);

        return keyHolder.getKey().intValue();
    }

}
