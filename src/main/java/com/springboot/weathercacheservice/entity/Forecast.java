package com.springboot.weathercacheservice.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Forecast {
	@Id
	private String city;
	
	@Column(name = "data", columnDefinition = "json")
	@JsonRawValue
	private String data;
	
}
