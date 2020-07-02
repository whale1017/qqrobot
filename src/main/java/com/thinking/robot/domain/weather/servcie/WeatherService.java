package com.thinking.robot.domain.weather.servcie;

import com.thinking.robot.domain.weather.data.DailyWeatherDto;

public interface WeatherService {
    
    DailyWeatherDto searchDailyWeather(final String location, final String start, final int days);
    
}
