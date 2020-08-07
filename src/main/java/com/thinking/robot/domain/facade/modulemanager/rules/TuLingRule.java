package com.thinking.robot.domain.facade.modulemanager.rules;

import com.thinking.robot.application.task.listener.assembler.MessageAssembler;
import com.thinking.robot.domain.facade.modulemanager.ModuleManager;
import com.thinking.robot.domain.facade.tuling.data.EventInfo;
import com.thinking.robot.domain.facade.tuling.data.TuLingResponseData;
import net.mamoe.mirai.message.MessageEvent;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.PlainText;

import java.util.List;

public class TuLingRule extends BaseRule {
    private static final int LEVEL = 100;
    
    
    public TuLingRule(ModuleManager moduleManager) {
        super(moduleManager, LEVEL);
    }
    
    @Override
    public boolean checkAccess(MessageEvent event) {
        if (event == null) {
            return false;
        }
        PlainText text = event.getMessage().first(PlainText.Key);
        return text != null;
    }
    
    @Override
    public List<Message> buildRecord(MessageEvent event) {
        EventInfo eventInfo = new EventInfo()
                .setUserId(event.getSender().getId())
                .setUserIdName(event.getSenderName());
    
        PlainText text = event.getMessage().first(PlainText.Key);
        
        final TuLingResponseData response = moduleManager.getTuLingService().getRecallByText(text.getContent(), eventInfo);
        final List<Message> messages = MessageAssembler.assemblerToMessageList(response.getResults());
        return messages;
    }
}
