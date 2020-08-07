package com.thinking.robot.domain.facade.tuling.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class TuLingRobotInfo {
    private String apiKey;
    private String description;
    
    public TuLingRobotInfo(final String apiKey, final String description){
        this.apiKey = apiKey;
        this.description = description;
    }
}
