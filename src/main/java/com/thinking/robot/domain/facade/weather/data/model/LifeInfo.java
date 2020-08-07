package com.thinking.robot.domain.facade.weather.data.model;

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
public class LifeInfo extends BaseInfo {
    
    private LifeSuggestion suggestion;
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LifeSuggestion{
    
        @JsonProperty("car_washing")
        private Parameter carWashing;
    
        private Parameter dressing;
    
        private Parameter flu;
    
        private Parameter sport;
    
        private Parameter travel;
    
        private Parameter uv;
    }
    
    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Parameter{
        private String brief;
        private String details;
    }
}
