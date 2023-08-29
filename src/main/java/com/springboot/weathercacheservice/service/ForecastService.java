package com.springboot.weathercacheservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.weathercacheservice.dto.WeatherData;
import com.springboot.weathercacheservice.dto.WeatherDetails;
import com.springboot.weathercacheservice.entity.Forecast;
import com.springboot.weathercacheservice.exception.ForecastNotAvailableException;
import com.springboot.weathercacheservice.repository.ForecastRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ForecastService {

    @Autowired
    ForecastRepository repository;

    public String getWeatherData(String city) throws ForecastNotAvailableException {
        Optional<Forecast> optional = repository.findById(city);
        if(optional.isEmpty()){
            throw new ForecastNotAvailableException("In cache forecast is not available for city "+ city);
        }


        String data = optional.get().getData();
        ObjectMapper mapper = new ObjectMapper();
        WeatherData wData = null;
        try {
            wData = mapper.readValue(data, WeatherData.class);
            List<WeatherDetails> list = wData.getList();
            if(list != null){
                WeatherDetails wDetails=list.get(list.size()-1);
                boolean isStale = wDetails.getDt_txt().isBefore(LocalDateTime.now().minusDays(3));
                log.info("[WCS] is Cache data stale :" + isStale);
                if (isStale){
                    repository.deleteById(city);
                    throw new ForecastNotAvailableException("In cache forecast is not available for city "+ city);
                }
            }
        } catch (JsonProcessingException e) {
            throw new ForecastNotAvailableException("In cache forecast is not available for city "+ city);
        }
        return data;
    }

    public Forecast saveWeatherData(Forecast forecast){
       return repository.save(forecast);
    }
}
