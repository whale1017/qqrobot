package com.thinking.robot.domain.weather.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DailyWeather {
    private String date;
    
    @JsonProperty("text_day")
    private String textDay;
    
    @JsonProperty("code_day")
    private String codeDay;
    
    @JsonProperty("text_night")
    private String textNight;
    
    @JsonProperty("code_night")
    private String codeNight;
    
    private String high;
    private String low;
    private String precip;
    
    @JsonProperty("wind_direction")
    private String windDirection;
    
    @JsonProperty("wind_direction_degree")
    private String windDirectionDegree;
    
    @JsonProperty("wind_speed")
    private String windSpeed;
    
    @JsonProperty("wind_scale")
    private String windScale;
    
    private String rainfall;
    
    private String humidity;
}
