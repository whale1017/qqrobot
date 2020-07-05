package com.thinking.robot.application.task.listener;

import com.thinking.robot.application.task.data.RobotInfo;
import com.thinking.robot.domain.modulemanager.RuleExecuteService;
import net.mamoe.mirai.event.SimpleListenerHost;

public class BaseListener extends SimpleListenerHost {
    protected RobotInfo robotInfo;
    
    protected RuleExecuteService ruleExecuteService;
    
    public BaseListener(final RobotInfo robotInfo, final RuleExecuteService ruleExecuteService){
        super();
        this.robotInfo = robotInfo;
        this.ruleExecuteService = ruleExecuteService;
    }
}
