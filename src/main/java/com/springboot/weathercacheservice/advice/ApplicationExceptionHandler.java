package com.springboot.weathercacheservice.advice;

import com.springboot.weathercacheservice.exception.ErrorDetails;
import com.springboot.weathercacheservice.exception.ForecastNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ForecastNotAvailableException.class)
    public ErrorDetails handlerForecastNotAvailableException(ForecastNotAvailableException ex){
        //Map<String, String> errorMap = new HashMap<>();
        //errorMap.put("errorMessage",ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode(1);
        errorDetails.setErrorMessage(ex.getMessage());
        return errorDetails;
    }
}
