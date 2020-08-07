package com.thinking.robot.domain.facade.weather.servcie;

import com.thinking.robot.domain.facade.weather.data.model.BaseDto;

public interface LifeService {
    BaseDto searchLifeInfo(String location);
}
