package com.thinking.robot.domain.modulemanager;

import com.thinking.robot.domain.tuling.service.TuLingService;
import com.thinking.robot.domain.weather.servcie.WeatherService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ModuleManager {
    
    @Autowired
    private TuLingService tuLingService;
    
    @Autowired
    private WeatherService weatherService;
    
}
