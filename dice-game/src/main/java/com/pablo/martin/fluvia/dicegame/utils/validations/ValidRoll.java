package com.pablo.martin.fluvia.dicegame.utils.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=RollValidator.class)
public @interface ValidRoll {
    String message() default "{game.roll}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
