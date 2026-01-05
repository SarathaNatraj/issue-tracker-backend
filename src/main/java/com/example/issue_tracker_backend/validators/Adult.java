package com.example.issue_tracker_backend.validators;

import java.lang.annotation.Target;
import java.lang.annotation.*;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;




@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdultValidator.class)

//@Adult
public @interface Adult {
    String message() default "Must be at least {value} years old";
    int value() default 18;
    Class<?>[] groups() default {};   // REQUIRED

    Class<? extends Payload>[] payload() default {};  // REQUIRED



}
