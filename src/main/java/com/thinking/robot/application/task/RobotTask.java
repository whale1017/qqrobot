package com.thinking.robot.application.task;

import com.thinking.robot.domain.modulemanager.ModuleManager;
import com.thinking.robot.domain.tuling.service.TuLingService;
import com.thinking.robot.model.QQRobotBuilder;
import com.thinking.robot.application.task.data.RobotInfo;
import com.thinking.robot.domain.tuling.service.impl.TuLingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RobotTask {
    @Autowired
    private RobotInfo robotInfo;
    
    @Autowired
    private ModuleManager moduleManager;
    
    @Scheduled(fixedDelay = 1000)
    public void checkBot(){
        QQRobotBuilder.initAndGetRobot(robotInfo, moduleManager);
    }
}
