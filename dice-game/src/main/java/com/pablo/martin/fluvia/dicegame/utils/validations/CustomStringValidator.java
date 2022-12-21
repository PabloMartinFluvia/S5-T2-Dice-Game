package com.pablo.martin.fluvia.dicegame.utils.validations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:values.properties")
public class CustomStringValidator implements ConstraintValidator<ValidString,String> {

    @Value("${username.length.min}")
    private int min;

    @Value("${username.length.max}")
    private int max;

    @Override
    public void initialize(ValidString constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s!=null && s.length()>= min && s.length()<= max;
    }
}
