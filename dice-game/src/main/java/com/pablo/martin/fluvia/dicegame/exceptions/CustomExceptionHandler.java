package com.pablo.martin.fluvia.dicegame.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

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
