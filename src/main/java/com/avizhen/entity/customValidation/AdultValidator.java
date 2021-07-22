package com.avizhen.entity.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


public class AdultValidator implements ConstraintValidator<Adult, Date> {
    private static final int ADULT_AGE = 18;

    @Override
    public boolean isValid(Date dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate localDate = Instant.ofEpochMilli(dateOfBirth.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return LocalDate.now().minusYears(ADULT_AGE).isAfter(localDate);
    }
}