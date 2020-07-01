package com.thinking.robot.listener;

import com.thinking.robot.listener.data.MessageData;
import com.thinking.robot.model.RobotInfo;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.message.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author nanke
 * @date 2020/6/28 下午2:22
 */
public class FriendListener extends BaseListener {
    
    public FriendListener(RobotInfo robotInfo) {
        super(robotInfo);
    }
    
    /**
     * Java方法级别注解,标注一个方法为事件监听器
     * @param event
     */
    @EventHandler
    public void onMessage(FriendMessageEvent event) {
    
        /**
         * 消息链
         */
        final MessageChain messageChain = event.getMessage();
        final MessageData messageData = new MessageData(messageChain);
    
        /**
         * 向事件触发群发送,触发式发送信息
         */
        final MessageChainBuilder builder = new MessageChainBuilder();
        builder.addAllFlatten(messageData.getMessagesWithoutAt());
        event.getSender().sendMessage(builder.build());
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        /**
         * 异常处理方式
         * 先直接打印堆栈吧～
         */
        exception.printStackTrace();
    }
}
