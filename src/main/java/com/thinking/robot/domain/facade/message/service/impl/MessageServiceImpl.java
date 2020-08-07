package com.thinking.robot.domain.facade.message.service.impl;

import com.thinking.robot.domain.facade.message.service.MessageService;
import com.thinking.robot.domain.service.QQRobotBuilder;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MessageServiceImpl implements MessageService {
    
    @Autowired
    private QQRobotBuilder qqRobotBuilder;
    
    @Override
    public void sendMessage(final Contact contact, final List<Message> messageList){
        
        final MessageChainBuilder builder = new MessageChainBuilder();
        builder.addAllFlatten(messageList);
        
        contact.sendMessage(builder.build());
    }
}
