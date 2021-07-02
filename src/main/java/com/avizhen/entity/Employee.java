package com.avizhen.entity;

import com.avizhen.enums.Gender;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private Gender gender;
    private Date birthDate;
    private Department department;


}
