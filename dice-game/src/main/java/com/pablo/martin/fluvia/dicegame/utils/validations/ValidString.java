package com.pablo.martin.fluvia.dicegame.utils.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=CustomStringValidator.class)
public @interface ValidString {
    String message() default "{game.username}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
