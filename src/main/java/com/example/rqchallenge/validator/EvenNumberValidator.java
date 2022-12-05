package com.example.rqchallenge.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EvenNumberValidator implements ConstraintValidator<EvenNumber, Long> {

    @Override
    public void initialize(EvenNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {

        return aLong % 2 == 0 ? true : false;
    }

}
