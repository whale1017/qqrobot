package com.thinking.robot.application.task.listener;

import com.thinking.robot.application.task.data.RobotInfo;
import com.thinking.robot.application.task.listener.assembler.MessageAssembler;
import com.thinking.robot.application.task.listener.data.MessageData;
import com.thinking.robot.domain.modulemanager.ModuleManager;
import com.thinking.robot.domain.tuling.data.EventInfo;
import com.thinking.robot.domain.tuling.data.TuLingResponseData;
import com.thinking.robot.domain.weather.assembler.DailyWeatherAssembler;
import com.thinking.robot.domain.weather.data.model.BaseDto;
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
import java.util.Objects;

/**
 * @author nanke
 * @date 2020/7/1 下午12:26
 */
public class GroupListener extends BaseListener {
    public GroupListener(RobotInfo robotInfo, ModuleManager moduleManager) {
        super(robotInfo, moduleManager);
    }
    
    @EventHandler
    public void onMessage(GroupMessageEvent event)  {

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
                String[] strings = text.getContent().trim().split(" ");
                if(strings.length >=2 && Objects.equals(strings[0], "天气")){
                    BaseDto dto = moduleManager.getWeatherService().searchDailyWeather(strings[1], "0", 3);
                    builder.add(DailyWeatherAssembler.dailyWeatherDtoAssemblerToText(dto));
                } else if(strings.length >=2 && Objects.equals(strings[0], "生活指数")){
                    BaseDto dto = moduleManager.getLifeService().searchLifeInfo(strings[1]);
                    builder.add(DailyWeatherAssembler.dailyWeatherDtoAssemblerToText(dto));
                } else {
                    EventInfo eventInfo = new EventInfo()
                            .setUserId(event.getSender().getId())
                            .setUserIdName(event.getSenderName());
                    final TuLingResponseData response = moduleManager.getTuLingService().getRecallByText(text.getContent(), eventInfo);
                    final List<Message> messages = MessageAssembler.assemblerToMessageList(response.getResults());
                    builder.addAllFlatten(messages);
                }
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
