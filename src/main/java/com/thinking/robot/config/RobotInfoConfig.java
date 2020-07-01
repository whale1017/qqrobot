package com.thinking.robot.config;

import com.thinking.robot.model.RobotInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class RobotInfoConfig {
    @Value("${qq.id}")
    private Long qqId;
    @Value("${qq.password}")
    private String qqPassword;
    
    @Bean("robotInfo")
    public RobotInfo robotInfo(){
        RobotInfo robotInfo = new RobotInfo();
        robotInfo.setQqId(qqId);
        robotInfo.setPassword(qqPassword);
        return robotInfo;
    }

}
