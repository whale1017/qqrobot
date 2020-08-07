package com.thinking.robot.domain.facade.modulemanager;

import com.thinking.robot.domain.facade.tuling.service.TuLingService;
import com.thinking.robot.domain.facade.weather.servcie.LifeService;
import com.thinking.robot.domain.facade.weather.servcie.WeatherService;
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
    
    @Autowired
    private LifeService lifeService;
    
}
