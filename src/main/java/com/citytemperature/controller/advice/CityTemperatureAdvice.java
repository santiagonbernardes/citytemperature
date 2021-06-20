package com.citytemperature.controller.advice;

import com.citytemperature.exceptions.CityNotFoundException;
import com.citytemperature.exceptions.CityTemperatureNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class CityTemperatureAdvice {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleBadRequestException(final ConstraintViolationException ex) {
        return build(ex);
    }

    @ExceptionHandler(value = {CityNotFoundException.class, CityTemperatureNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleNotFoundException(final Exception ex) {
        return build(ex);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails handleUnexpectedException(final Exception ex) {
        ex.printStackTrace();
        return new ErrorDetails("An error has occurred and your team is working on it. Try again later.");
    }

    private ErrorDetails build(final Exception ex) {
        ex.printStackTrace();
        return new ErrorDetails(ex.getMessage());
    }
}
