package com.thinking.robot.application.task.listener;

import com.thinking.robot.application.task.data.RobotInfo;
import com.thinking.robot.domain.modulemanager.ModuleManager;
import net.mamoe.mirai.event.SimpleListenerHost;

public class BaseListener extends SimpleListenerHost {
    protected RobotInfo robotInfo;
    
    protected ModuleManager moduleManager;
    
    public BaseListener(final RobotInfo robotInfo, final ModuleManager moduleManager){
        super();
        this.robotInfo = robotInfo;
        this.moduleManager = moduleManager;
    }
}
