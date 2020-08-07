package com.thinking.robot.domain.facade.weather.servcie;

import com.thinking.robot.domain.facade.weather.data.model.BaseDto;

public interface WeatherService {
    
    BaseDto searchDailyWeather(final String location, final String start, final int days);
    
}
