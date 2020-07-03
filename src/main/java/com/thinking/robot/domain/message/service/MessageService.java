package com.thinking.robot.domain.message.service;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.Message;

import java.util.List;

public interface MessageService {
    
    
    void sendMessage(final Contact contact, final List<Message> messageList);
}
