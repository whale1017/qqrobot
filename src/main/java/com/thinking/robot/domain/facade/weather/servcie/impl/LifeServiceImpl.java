package com.thinking.robot.domain.facade.weather.servcie.impl;

import com.thinking.robot.domain.facade.weather.data.Constants;
import com.thinking.robot.domain.facade.weather.data.model.BaseDto;
import com.thinking.robot.domain.facade.weather.data.model.LifeDto;
import com.thinking.robot.domain.facade.weather.servcie.LifeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class LifeServiceImpl implements LifeService {
    
    private static final String LIFE_PREFIX = Constants.ZHIXIN_WEATHER_PREFIX + "/life";
    
    private static final String TODAY = LIFE_PREFIX + "/suggestion.json?key={key}&location={location}";
    
    @Value("${weather.key}")
    private String weatherKey;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public BaseDto searchLifeInfo(String location) {
        
        final Map<String, Object> params = new HashMap<>();
        params.put("key", weatherKey);
        params.put("location", location);
        final BaseDto dailyWeatherDto;
        try {
            BaseDto lifeDto = restTemplate.getForObject(TODAY, LifeDto.class, params);
            return lifeDto;
        } catch (Exception e){
            log.error("searchLifeInfo failed", e);
            return null;
        }
        
    }
}
