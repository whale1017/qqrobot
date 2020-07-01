package com.thinking.robot.config;

import com.thinking.robot.model.RobotInfo;
import com.thinking.robot.tuling.data.TuLingRobotInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class TuLingRobotInfoConfig {
    @Value("${tuling.appkey}")
    private String appKey;
    @Value("${tuling.description}")
    private String description;
    
    @Bean("tuLingRobotInfo")
    public TuLingRobotInfo tuLingRobotInfo(){
        return new TuLingRobotInfo(appKey, description);
    }
}
