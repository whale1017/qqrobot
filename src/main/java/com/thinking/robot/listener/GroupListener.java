package com.thinking.robot.listener;

import com.thinking.robot.listener.assembler.MessageAssembler;
import com.thinking.robot.listener.data.MessageData;
import com.thinking.robot.model.RobotInfo;
import com.thinking.robot.tuling.data.EventInfo;
import com.thinking.robot.tuling.data.TuLingResponseData;
import com.thinking.robot.tuling.service.TuLingService;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author nanke
 * @date 2020/7/1 下午12:26
 */
public class GroupListener extends BaseListener {
    private TuLingService tuLingService;
    public GroupListener(RobotInfo robotInfo, TuLingService tuLingService) {
        super(robotInfo);
        this.tuLingService = tuLingService;
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
        if(true){
            PlainText text = messageChain.first(PlainText.Key);
            if(text != null){
                EventInfo eventInfo = new EventInfo()
                        .setUserId(event.getSender().getId())
                        .setUserIdName(event.getSenderName());
                final TuLingResponseData response = tuLingService.getRecallByText(text.getContent(), eventInfo);
                final List<Message> messages = MessageAssembler.assemblerToMessageList(response.getResults());
                builder.addAllFlatten(messages);
            } else {
                builder.add("图灵机无法回复");
            }
        } else {
            builder.addAllFlatten(messageData.getMessagesWithoutAt());
        }
        event.getGroup().sendMessage(builder.build());
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }
}
