package com.avizhen.test.testData;

import com.avizhen.entity.Department;
import com.avizhen.entity.Employee;
import com.avizhen.enums.Gender;

import java.sql.Date;
import java.util.*;

public class EmployeeTestData {
    private Map<Integer, Employee> employeesForTest = new HashMap<>();
    private static EmployeeTestData INSTANCE = null;

    private EmployeeTestData() {
        initEmployeeTestData();
    }

    public static EmployeeTestData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmployeeTestData();
        }
        return INSTANCE;
    }

    private void initEmployeeTestData() {
        saveTestEmployee(buildTestEmployee(1, "testFirstName", "testLastName"));
        saveTestEmployee(buildTestEmployee(2, "Ron", "Don"));
    }

    public Integer getNextId() {
        return employeesForTest.size() + 1;
    }

    public Employee findById(Integer id) {
        for (Employee employee : employeesForTest.values()) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public Employee deleteTestEmployee(Integer id) {
        Employee employeeToDelete = findById(id);
        if (employeeToDelete != null) {
            employeesForTest.remove(id);
        }
        return null;
    }

    private Employee buildTestEmployee(Integer id, String firstName, String lastName) {

        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setJobTitle("engineer");
        employee.setBirthDate(new Date(1220227200L * 1000));
        employee.setDepartment(new Department(1, "it", Collections.emptyList()));
        employee.setGender(Gender.MAN);
        return employee;
    }

    public List<Employee> getAllTestEmployees() {
        return new ArrayList<>(employeesForTest.values());
    }

    public Employee saveTestEmployee(Employee employee) {
        employeesForTest.put(getNextId(), employee);
        return employee;
    }

    public Employee updateTestUser(Integer id) {
        Employee byId = findById(id);
        return employeesForTest.put(id, byId);
    }
}
