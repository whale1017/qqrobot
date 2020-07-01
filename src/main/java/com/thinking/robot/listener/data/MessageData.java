package com.thinking.robot.listener.data;

import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageData {
    private List<Message> messages = new ArrayList<>();
    
    public MessageData(final MessageChain messageChain){
        messages.addAll(messageChain);
    }
    
    public List<Long> getAtList(){
        return messages.stream()
                .filter(message -> message instanceof At)
                .map(message -> ((At) message).getTarget())
                .collect(Collectors.toList());
    }
    
    public List<Message> getMessages() {
        return messages;
    }
    
    public List<Message> getMessagesWithoutAt() {
        return messages.stream()
                .filter(message -> !(message instanceof At) && !(message instanceof MessageSource))
                .collect(Collectors.toList());
    }
}
