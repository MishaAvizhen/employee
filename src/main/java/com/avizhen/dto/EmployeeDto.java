package com.avizhen.dto;

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
    private Date birthDate;
    private Integer departmentId;
}
