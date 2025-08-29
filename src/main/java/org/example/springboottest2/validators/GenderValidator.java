package org.example.springboottest2.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.springboottest2.enums.GenderEnum;

public class GenderValidator implements ConstraintValidator<ValidGender, GenderEnum> {
    @Override
    public boolean isValid(GenderEnum genderEnum, ConstraintValidatorContext context) {
        if (genderEnum == null) {
            return true;
        }
        return genderEnum.equals(GenderEnum.Male) || genderEnum.equals(GenderEnum.Female) || genderEnum.equals(GenderEnum.Other);
    }
}
