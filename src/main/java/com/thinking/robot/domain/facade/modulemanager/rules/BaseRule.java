package com.thinking.robot.domain.facade.modulemanager.rules;

import com.thinking.robot.domain.facade.modulemanager.ModuleManager;
import lombok.Getter;
import net.mamoe.mirai.message.MessageEvent;
import net.mamoe.mirai.message.data.Message;

import java.util.List;

@Getter
public abstract class BaseRule {
    
    protected ModuleManager moduleManager;
    
    private Integer level = 0;
    
    protected BaseRule(ModuleManager moduleManager, Integer level){
        this.moduleManager = moduleManager;
        this.level = level;
    }
    
    public abstract boolean checkAccess(MessageEvent event);
    
    public abstract List<Message> buildRecord(MessageEvent event);
    
    public int getLevel(){
        return level;
    }
}
