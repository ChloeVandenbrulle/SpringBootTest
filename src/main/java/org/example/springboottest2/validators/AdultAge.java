package org.example.springboottest2.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AdultAgeValidator.class})
@Documented
public @interface AdultAge {
    String message() default "User should be an adult";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
