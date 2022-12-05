package com.example.rqchallenge.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EvenNumberValidator.class)
@Documented
public @interface EvenNumber {
    String message() default "{EvenNumber.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
