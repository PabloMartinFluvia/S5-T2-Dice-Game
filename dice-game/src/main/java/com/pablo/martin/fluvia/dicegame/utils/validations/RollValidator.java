package com.pablo.martin.fluvia.dicegame.utils.validations;

import com.pablo.martin.fluvia.dicegame.domain.models.Roll;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.stream.IntStream;

@PropertySource("classpath:values.properties")
public class RollValidator implements ConstraintValidator<ValidRoll, Roll> {

    @Value("${dices.total}")
    private int numDices;

    @Value("${dices.value.max}")
    private int max;

    @Value("${dices.value.min}")
    private int min;

    @Override
    public void initialize(ValidRoll constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Roll roll, ConstraintValidatorContext constraintValidatorContext) {
        int[] dices = roll.getDices();
        IntStream values = Arrays.stream(dices);
        return dices.length == numDices && values.noneMatch(v -> v<min || v>max );
    }
}
