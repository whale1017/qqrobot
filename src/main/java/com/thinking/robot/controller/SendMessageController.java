package com.thinking.robot.controller;


import com.thinking.robot.application.task.RobotTask;
import com.thinking.robot.domain.facade.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private RobotTask robotTask;
    
    @GetMapping("/message/test")
    public void testMessage(){
//        messageService.sendMessage();
        robotTask.leetCode();
    }
}
