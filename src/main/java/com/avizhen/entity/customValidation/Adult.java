package com.avizhen.entity.customValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({FIELD})
@Constraint(validatedBy = AdultValidator.class)
public @interface Adult {
    String message() default "{Incorrect age}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}