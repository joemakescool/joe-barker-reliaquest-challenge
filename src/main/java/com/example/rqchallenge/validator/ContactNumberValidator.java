package com.example.rqchallenge.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {

    @Override
    public void initialize(ContactNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        if (value.length() > 1) {
            return true;
        } else {
            return false;
        }
    }
}
