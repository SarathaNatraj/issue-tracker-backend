package com.example.issue_tracker_backend.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdultValidator implements ConstraintValidator<Adult, Integer> {

	private int requiredAge;
	

    @Override
    public void initialize(Adult constraint) {
        this.requiredAge = constraint.value();
    }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext ctx) {
        return age != null && age >= requiredAge;
    }


}