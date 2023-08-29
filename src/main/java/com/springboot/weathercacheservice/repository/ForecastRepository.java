package com.springboot.weathercacheservice.repository;

import com.springboot.weathercacheservice.entity.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastRepository extends JpaRepository<Forecast,String> {
}
