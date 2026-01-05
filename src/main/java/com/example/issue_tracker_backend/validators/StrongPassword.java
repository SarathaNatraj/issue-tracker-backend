package com.example.issue_tracker_backend.validators;
import java.lang.annotation.Target;
import java.lang.annotation.*;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StrongPasswordValidator.class)
public @interface StrongPassword {
    String message() default "Password must contain uppercase, lowercase, digit & symbol";
    Class<?>[] groups() default {}; //required
    Class<? extends Payload>[] payload() default {}; //required
}
