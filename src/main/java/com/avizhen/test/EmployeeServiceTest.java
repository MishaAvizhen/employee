package com.avizhen.test;


import com.avizhen.dao.EmployeeDao;
import com.avizhen.entity.Department;
import com.avizhen.entity.Employee;
import com.avizhen.enums.Gender;
import com.avizhen.service.impl.EmployeeServiceImpl;
import com.avizhen.test.testData.EmployeeTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeServiceTest {
    private EmployeeTestData employeeTestData = EmployeeTestData.getInstance();
    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Before
    public void setUp() throws Exception {
        when(employeeDao.getAllEmployees()).thenReturn(employeeTestData.getAllTestEmployees());
        when(employeeDao.getById(any(Integer.class))).thenAnswer(i -> employeeTestData.findById((Integer) i.getArguments()[0]));
        when(employeeDao.updateEmployee(any(Integer.class),any((Employee.class)))).thenAnswer(i -> employeeTestData.updateTestUser((Integer) i.getArguments()[0]));
        when(employeeDao.createEmployee(any((Employee.class)))).thenAnswer(i -> employeeTestData.saveTestEmployee((Employee) i.getArguments()[0]));
        doAnswer(i -> employeeTestData.deleteTestEmployee((Integer) i.getArguments()[0])).when(employeeDao).deleteEmployee(any(Integer.class));
    }

    @Test
    public void findAll() throws Exception {
        List<Employee> expectedTestEmployees = employeeTestData.getAllTestEmployees();
        List<Employee> actualTestEmployees = employeeService.findAll();
        Assert.assertEquals(expectedTestEmployees.size(), actualTestEmployees.size());
    }

    @Test
    public void findEmployeeById() throws Exception {
        Integer id = 1;
        Employee expectedEmployee = employeeTestData.findById(id);
        Employee actualEmployee = employeeService.findById(id);
        Assert.assertEquals("Employee not equals", expectedEmployee, actualEmployee);
    }

    @Test
    public void createEmployee() throws Exception {
        Integer id = employeeTestData.getNextId();
        String firstName = " newFirstNameTestEmployee";
        String lastName = " newLastNameTestEmployee";
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setJobTitle("engineer");
        employee.setBirthDate(new Date(1220227200L * 1000));
        employee.setDepartment(new Department(2, "economics"));
        employee.setGender(Gender.WOMAN);
        employeeTestData.saveTestEmployee(employee);
        Employee actualEmployee = employeeService.createEmployee(employee);
        Assert.assertNotNull("Created Employee equals null ", actualEmployee);
    }

    @Test
    public void updateEmployee() throws Exception {
        Integer id = 2;
        Employee employeeToUpdate = employeeTestData.findById(id);
        Assert.assertNotNull("Employee not found", employeeToUpdate);
        String expectedFirstName = employeeToUpdate.getFirstName();
        employeeToUpdate.setFirstName("UpdateFirstName");
        employeeToUpdate.setLastName("UpdateLastName");
        employeeToUpdate.setJobTitle("engineer");
        employeeToUpdate.setBirthDate(new Date(1220227200L * 1000));
        employeeToUpdate.setDepartment(new Department(2, "economics"));
        employeeToUpdate.setGender(Gender.MAN);
        Employee employeeAfterUpdate = employeeTestData.updateTestUser(id);
        Employee actualEmployee = employeeService.updateEmployee(id, employeeAfterUpdate);
        String actualFirstName = actualEmployee.getFirstName();
        Assert.assertEquals("Employee not equals", employeeAfterUpdate, actualEmployee);
        Assert.assertNotEquals("FirstName wasn't update", expectedFirstName, actualFirstName);
    }

    @Test
    public void deleteEmployee() throws Exception {
        Integer id = 1;
        employeeService.deleteEmployee(id);
        Employee employeeAfterDelete = employeeTestData.findById(id);
        Assert.assertNull("Employee was not delete", employeeAfterDelete);
    }
}