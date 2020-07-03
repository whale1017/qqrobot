package com.thinking.robot.domain.weather.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.thinking.robot.domain.weather.data.model.BaseInfo;
import com.thinking.robot.domain.weather.data.model.DailyWeatherInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DailyDto implements BaseDto{
    private List<DailyWeatherInfo> results;
}
