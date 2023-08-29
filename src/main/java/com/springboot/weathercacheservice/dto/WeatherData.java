package com.springboot.weathercacheservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeatherData {
	private String cod;
	private String message;
	private int cnt;
	private List<WeatherDetails> list;
	private CityDetails city;
}
