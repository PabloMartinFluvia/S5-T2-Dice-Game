package com.pablo.martin.fluvia.dicegame.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MissingServletRequestParameterException.class, //required parameter is missing in request
            HttpMessageNotReadableException.class //problems reading the request
            }) //
    public ApiErrorResponse handleBadRequest(Exception ex){
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @Valid argument fails
    @ExceptionHandler(MethodArgumentNotValidException.class )
    protected ApiErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Stream<String> errorFields = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage());

        Stream<String> errorsGlobals = ex.getBindingResult().getGlobalErrors().stream()
                .map(globalError -> globalError.getObjectName() + ": " + globalError.getDefaultMessage());
        String[] errors = Stream.concat(errorFields,errorsGlobals).toArray(String[]::new);
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST,ex,errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // value type provided impossible formatting to required type
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiErrorResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) throws NullPointerException{
        String cause = ex.getName() + " value can't be parsed to type " + ex.getRequiredType().getName();
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST,ex, cause);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class )
    public ApiErrorResponse handleConstraintViolation(ConstraintViolationException ex) {
        String[] errors = ex.getConstraintViolations()
                .stream().map(violation -> violation.getMessage()).toArray(String[]::new);
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST,ex, errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NoHandlerFoundException.class,
            PlayerNotFoundException.class})
    public ApiErrorResponse handleNotFound(Exception ex){
        return new ApiErrorResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UsernameNotAvailableException.class)
    public ApiErrorResponse handleNameNotAvailable(Exception ex){
        return new ApiErrorResponse(HttpStatus.CONFLICT, ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleCriticException(Exception ex){
        ex.printStackTrace(); //must be solved or handled when is detected
        return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
}
