package org.example.springboottest2.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AdultAgeValidator implements ConstraintValidator<AdultAge, LocalDate> {
    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext context) {
        if (birthdate == null) {
            return true;
        }
        int age = Period.between(birthdate, LocalDate.now()).getYears();
        return age >= 18;
    }
}
