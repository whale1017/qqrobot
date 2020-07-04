package com.thinking.robot.application.task;

import com.thinking.robot.domain.leetcode.data.Question;
import com.thinking.robot.domain.leetcode.service.LeetCodeService;
import com.thinking.robot.domain.message.service.MessageService;
import com.thinking.robot.model.QQRobotBuilder;
import com.thinking.robot.utils.HtmlToImageUtils;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.PlainText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class RobotTask {
    
    @Autowired
    private QQRobotBuilder qqRobotBuilder;
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private LeetCodeService leetCodeService;
    
    @Value("${leetcode.groupid}")
    private Long groupId;
    
    @Scheduled(fixedDelay = 10000)
    public void checkBot(){
        log.info("check bot connection");
        qqRobotBuilder.initAndGetRobot();
    }
    
    @Scheduled(cron = "0 0 9 * * ?")
    public void leetCode(){
    
        final Question question = leetCodeService.getTodayQuestion();
        
        final List<Message> messageList = new LinkedList<>();
        
        final BufferedImage image = HtmlToImageUtils.html2Img(question.getTranslatedContent());
        final Contact contact = qqRobotBuilder.initAndGetRobot().getGroup(groupId);
        final Message message = contact.uploadImage(image);
        
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        final Message dateMessage = new PlainText("每日一题  " + dateNowStr);
        final Message questionLink = new PlainText("题目链接:\nhttps://leetcode-cn.com/problems/" + question.getTitleSlug() + "/");
        
        messageList.add(AtAll.INSTANCE);
        messageList.add(new PlainText("\n"));
        messageList.add(dateMessage);
        messageList.add(new PlainText("\n"));
        messageList.add(message);
        messageList.add(new PlainText("\n"));
        messageList.add(questionLink);
    
        messageService.sendMessage(contact, messageList);
    }
}
