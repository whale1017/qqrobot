package com.thinking.robot.application.task.listener.assembler;

import com.thinking.robot.domain.tuling.data.TuLingResponseData;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.PlainText;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MessageAssembler {
    
    private final static String[] RESULT_TYPE = {"text", "url"};
    private final static String TEXT = "text";
    private final static String URL = "url";
    
    
    public static Message assemblerToMessage(TuLingResponseData.Result result){
        if(result == null){
            return null;
        }
        final String resultType = result.getResultType();
        if(Objects.equals(resultType, TEXT)){
            return new PlainText(result.getValues().getText());
        } else if(Objects.equals(resultType, URL)){
            return new PlainText("url: " + result.getValues().getUrl());
        }
        return null;
    }
    
    public static List<Message> assemblerToMessageList(List<TuLingResponseData.Result> results){
        if(CollectionUtils.isEmpty(results)){
            return Collections.emptyList();
        }
        List<Message> messageList = new LinkedList<>();
        for (TuLingResponseData.Result result : results) {
            final Message message = assemblerToMessage(result);
            if(message != null){
                messageList.add(message);
            }
        }
        return messageList;
    }
}
