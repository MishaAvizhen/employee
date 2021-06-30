package com.avizhen.dao.mapper;

import com.avizhen.dao.DepartmentDao;
import com.avizhen.entity.Department;
import com.avizhen.entity.Employee;
import com.avizhen.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeMapper implements RowMapper<Employee> {

    private DepartmentDao departmentDao;

    @Autowired
    public EmployeeMapper(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = departmentDao.findDepartmentById(resultSet.getInt("department_id"));
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setDepartment(department);
        employee.setGender(Gender.valueOf(resultSet.getString("gender")));
        employee.setJobTitle(resultSet.getString("job_title"));
        employee.setBirthDate(resultSet.getDate("date_of_birth"));
        return employee;
    }

}
