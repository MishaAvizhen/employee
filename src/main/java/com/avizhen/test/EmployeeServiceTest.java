package com.avizhen.test;


import com.avizhen.converter.impl.EmployeeConverter;
import com.avizhen.dto.EmployeeDto;
import com.avizhen.entity.Department;
import com.avizhen.entity.Employee;
import com.avizhen.enums.Gender;
import com.avizhen.repository.EmployeeRepository;
import com.avizhen.service.impl.EmployeeServiceImpl;
import com.avizhen.test.testData.EmployeeTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeServiceTest {
    private EmployeeTestData employeeTestData = EmployeeTestData.getInstance();
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Spy
    private EmployeeConverter employeeConverter = new EmployeeConverter();

    @Before
    public void setUp() throws Exception {
        when(employeeRepository.findAll()).thenReturn(employeeTestData.getAllTestEmployees());
        when(employeeRepository.getOne(any(Integer.class))).thenAnswer(i -> employeeTestData.findById((Integer) i.getArguments()[0]));
        when(employeeRepository.saveAndFlush(any((Employee.class)))).thenAnswer(i -> employeeTestData.updateTestUser((Integer) i.getArguments()[0]));
        when(employeeRepository.save(any((Employee.class)))).thenAnswer(i -> employeeTestData.saveTestEmployee((Employee) i.getArguments()[0]));
        doAnswer(i -> employeeTestData.deleteTestEmployee((Integer) i.getArguments()[0])).when(employeeRepository).deleteById(any(Integer.class));
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
        Department department = new Department();
        department.setId(1);
        department.setName("it");

        Integer id = employeeTestData.getNextId();
        String firstName = " newFirstNameTestEmployee";
        String lastName = " newLastNameTestEmployee";
        EmployeeDto employee = new EmployeeDto();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setJobTitle("engineer");
        employee.setBirthDate(new Date(1220227200L * 1000));
        employee.setDepartmentId(1);
        employee.setGender(Gender.WOMAN);
        Employee convertToEntity = employeeConverter.convertToEntity(employee);
        employeeTestData.saveTestEmployee(convertToEntity);
        Employee actualEmployee = employeeService.createEmployee(employee);
        Assert.assertNotNull("Created Employee equals null ", actualEmployee);
    }

    @Test
    public void updateEmployee() throws Exception {

        Integer id = 2;
        Employee employeeToUpdate = employeeTestData.findById(id);
        Assert.assertNotNull("Employee not found", employeeToUpdate);
        String expectedFirstName = employeeToUpdate.getFirstName();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("UpdateFirstName");
        employeeDto.setLastName("UpdateLastName");
        employeeDto.setJobTitle("engineer");
        employeeDto.setBirthDate(new Date(1220227200L * 1000));
        employeeDto.setDepartmentId(1);
        employeeDto.setGender(Gender.MAN);
        Employee employeeAfterUpdate = employeeTestData.updateTestUser(id);
        Employee actualEmployee = employeeService.updateEmployee(id, employeeDto);
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