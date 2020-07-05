package com.thinking.robot.application.task.listener;

import com.thinking.robot.application.task.data.RobotInfo;
import com.thinking.robot.domain.modulemanager.RuleExecuteService;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nanke
 * @date 2020/7/1 下午12:26
 */
public class GroupListener extends BaseListener {
    public GroupListener(RobotInfo robotInfo, RuleExecuteService ruleExecuteService) {
        super(robotInfo, ruleExecuteService);
    }
    
    @EventHandler
    public void onMessage(GroupMessageEvent event)  {

        /**
         * 消息链
         */
        final MessageChain messageChain = event.getMessage();
        
        /**
         * 判断是否 @当前机器人
         */
        List<Long> atList = messageChain.stream()
                .filter(message -> message instanceof At)
                .map(message -> ((At) message).getTarget())
                .collect(Collectors.toList());
        if (!atList.contains(robotInfo.getQqId())) {
            return;
        }
        
        /**
         * 向事件触发群发送,触发式发送信息
         */
        List<List<Message>> result = ruleExecuteService.getRuleQueue(event);
        for (List<Message> messageList : result) {
            final MessageChainBuilder builder = new MessageChainBuilder();
            builder.add(new At(event.getSender()));
            builder.addAllFlatten(messageList);
            event.getGroup().sendMessage(builder.build());
        }
    }
    

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }
}
