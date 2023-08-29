package com.springboot.weathercacheservice.exception;

import lombok.Data;

@Data
public class ErrorDetails {
    private int errorCode;
    private String errorMessage;
}
