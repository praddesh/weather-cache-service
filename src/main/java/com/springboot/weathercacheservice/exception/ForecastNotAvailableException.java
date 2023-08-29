package com.springboot.weathercacheservice.exception;

public class ForecastNotAvailableException extends  Exception{

    public ForecastNotAvailableException(String message){
        super(message);
    }
}
