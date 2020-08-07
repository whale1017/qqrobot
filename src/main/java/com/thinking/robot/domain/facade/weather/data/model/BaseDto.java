package com.thinking.robot.domain.facade.weather.data.model;

import java.util.List;

public interface BaseDto {
    List<? extends BaseInfo> getResults();
}
