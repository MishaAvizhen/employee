//package com.avizhen.converter.impl;
//
//import com.avizhen.converter.Converter;
//import com.avizhen.entity.Employee;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EmployeeConverter implements Converter<Employee, EmployeeDto> {
//
//
//    @Override
//    public com.avizhen.entity.Employee convertToEntity(EmployeeDto dto) {
//        com.avizhen.entity.Employee employee = new com.avizhen.entity.Employee();
//
//        return convertToExistingEntity(dto, employee);
//    }
//
//    @Override
//    public com.avizhen.entity.Employee convertToExistingEntity(EmployeeDto dto, com.avizhen.entity.Employee entity) {
//        com.avizhen.entity.Employee employee = new com.avizhen.entity.Employee();
//        employee.setFirstName(entity.getFirstName());
//        employee.setLastName(entity.getLastName());
//        employee.setBirthDate(entity.getBirthDate());
//        employee.setJobTitle(entity.getJobTitle());
//        employee.setGender(entity.getGender());
//        employee.setDepartmentId(entity.getDepartmentId());
//        return employee;
//    }
//}
