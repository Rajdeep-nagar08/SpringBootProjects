package com.example.UserDefinedAnnotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
// I want to use this annotation on feild (e.g. email feild)
// If I want to use it on Method ot Type , I can mention ElementType.Method, ElementType.Type

@Retention(RetentionPolicy.RUNTIME)
// it will be activated as long as application is running

@Constraint(validatedBy = {UniqueValidation.class})

//

public @interface UniqueEmailValidator {



    public String message() default "Email address already exist";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
