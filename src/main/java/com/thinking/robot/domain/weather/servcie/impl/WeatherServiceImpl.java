package com.thinking.robot.domain.weather.servcie.impl;

import com.thinking.robot.domain.weather.data.DailyWeatherDto;
import com.thinking.robot.domain.weather.servcie.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String WEATHER_PREFIX = "https://api.seniverse.com/v3/weather";
    
    private static final String DAILY = WEATHER_PREFIX + "/daily.json?key={key}&location={location}&start={start}&days={days}";
    
    @Value("${weather.key}")
    private String weatherKey;
    
    @Override
    public DailyWeatherDto searchDailyWeather(String location, String start, int days) {
        
        final Map<String, Object> params = new HashMap<>();
        params.put("key", weatherKey);
        params.put("location", location);
        params.put("start", 0);
        params.put("days", days);
        final DailyWeatherDto dailyWeatherDto;
        try {
            dailyWeatherDto = restTemplate.getForObject(DAILY, DailyWeatherDto.class, params);
            return dailyWeatherDto;
        } catch (Exception e){
            log.error("searchDailyWeather failed", e);
            return null;
        }
        
    }
    
}
