package com.thinking.robot.domain.weather.servcie;

import com.thinking.robot.domain.weather.data.model.BaseDto;

public interface WeatherService {
    
    BaseDto searchDailyWeather(final String location, final String start, final int days);
    
}
