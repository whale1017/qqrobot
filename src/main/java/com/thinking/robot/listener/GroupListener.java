package com.thinking.robot.listener;

import com.thinking.robot.listener.data.MessageData;
import com.thinking.robot.model.RobotInfo;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author nanke
 * @date 2020/7/1 下午12:26
 */
public class GroupListener extends BaseListener {
    
    public GroupListener(RobotInfo robotInfo) {
        super(robotInfo);
    }
    
    @EventHandler
    public void onMessage(GroupMessageEvent event) throws InterruptedException {

        /**
         * 消息链
         */
        final MessageChain messageChain = event.getMessage();
        
        final MessageData messageData = new MessageData(messageChain);
        /**
         * 判断是否 @当前机器人
         */
        if (!messageData.getAtList().contains(robotInfo.getQqId())) {
            return;
        }
        
        /**
         * 向事件触发群发送,触发式发送信息
         */
        final MessageChainBuilder builder = new MessageChainBuilder();
        builder.add(new At(event.getSender()));
        builder.addAllFlatten(messageData.getMessagesWithoutAt());
        event.getGroup().sendMessage(builder.build());
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }
}
