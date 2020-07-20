package com.thinking.robot.domain.modulemanager;

import com.thinking.robot.domain.modulemanager.rules.BaseRule;
import com.thinking.robot.domain.modulemanager.rules.CompilerRule;
import com.thinking.robot.domain.modulemanager.rules.LifeInfoRule;
import com.thinking.robot.domain.modulemanager.rules.TuLingRule;
import com.thinking.robot.domain.modulemanager.rules.WeatherRule;
import net.mamoe.mirai.message.MessageEvent;
import net.mamoe.mirai.message.data.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Component
public class RuleExecuteService {
    
    @Autowired
    private ModuleManager moduleManager;
    
    private static List<BaseRule> ruleQueue;
    
    private void initRuleQueue(){
        if(ruleQueue == null){
            ruleQueue = new ArrayList<BaseRule>(Arrays.asList(
                    new LifeInfoRule(moduleManager),
                    new TuLingRule(moduleManager),
                    new CompilerRule(moduleManager),
                    new WeatherRule(moduleManager)
            ));
            ruleQueue.sort(Comparator.comparingInt(BaseRule::getLevel));
        }
    }
    
    public List<List<Message>> getRuleQueue(MessageEvent event) {
        initRuleQueue();
        final List<List<Message>> res = new LinkedList<>();
        for (BaseRule baseRule : ruleQueue) {
            if (!baseRule.checkAccess(event)) {
                continue;
            }
            List<Message> messages = baseRule.buildRecord(event);
            if(CollectionUtils.isEmpty(messages)){
                continue;
            }
            res.add(messages);
            break;
        }
        return res;
    }
}
