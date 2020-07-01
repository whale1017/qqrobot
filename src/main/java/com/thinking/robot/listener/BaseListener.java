package com.thinking.robot.listener;

import com.thinking.robot.model.RobotInfo;
import net.mamoe.mirai.event.SimpleListenerHost;

public class BaseListener extends SimpleListenerHost {
    protected RobotInfo robotInfo;
    
    public BaseListener(final RobotInfo robotInfo){
        super();
        this.robotInfo = robotInfo;
    }
}
