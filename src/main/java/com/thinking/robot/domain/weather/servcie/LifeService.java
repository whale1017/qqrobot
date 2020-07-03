package com.thinking.robot.domain.weather.servcie;

import com.thinking.robot.domain.weather.data.model.BaseDto;

public interface LifeService {
    BaseDto searchLifeInfo(String location);
}
