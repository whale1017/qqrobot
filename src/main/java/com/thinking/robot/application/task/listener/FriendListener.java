package com.thinking.robot.application.task.listener;

import com.thinking.robot.application.task.data.RobotInfo;
import com.thinking.robot.domain.facade.modulemanager.RuleExecuteService;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.message.FriendMessageEvent;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author nanke
 * @date 2020/6/28 下午2:22
 */
public class FriendListener extends BaseListener {
    
    public FriendListener(RobotInfo robotInfo, RuleExecuteService ruleExecuteService) {
        super(robotInfo, ruleExecuteService);
    }
    
    /**
     * Java方法级别注解,标注一个方法为事件监听器
     * @param event
     */
    @EventHandler
    public void onMessage(FriendMessageEvent event) {
        List<List<Message>> result = ruleExecuteService.getRuleQueue(event);
        for (List<Message> messageList : result) {
            final MessageChainBuilder builder = new MessageChainBuilder();
            builder.addAllFlatten(messageList);
            event.getSender().sendMessage(builder.build());
        }
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
