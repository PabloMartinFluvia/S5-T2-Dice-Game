package com.pablo.martin.fluvia.dicegame.utils.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Min(value = 1, message = "{game.id}")
@Target(PARAMETER)
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidId {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
