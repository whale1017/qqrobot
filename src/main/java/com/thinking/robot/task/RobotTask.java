package com.thinking.robot.task;

import com.thinking.robot.model.QQRobotBuilder;
import com.thinking.robot.model.RobotInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RobotTask {
    @Autowired
    private RobotInfo robotInfo;
    
    @Scheduled(fixedDelay = 1000)
    public void checkBot(){
        QQRobotBuilder.initAndGetRobot(robotInfo);
    }
}
