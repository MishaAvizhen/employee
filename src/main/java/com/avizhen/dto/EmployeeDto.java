package com.avizhen.dto;

import com.avizhen.entity.customValidation.Adult;
import com.avizhen.enums.Gender;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EmployeeDto {

    private String firstName;
    private String lastName;
    private String jobTitle;
    private Gender gender;
    @Adult(message = "Age must be 18+")
    private Date birthDate;
    private Integer departmentId;
}
