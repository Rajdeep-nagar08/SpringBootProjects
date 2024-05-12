package com.example.UserDefinedAnnotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueValidation implements ConstraintValidator<UniqueEmailValidator,String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {


        return false;
    }
}
