package com.thinking.robot.domain.tuling.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinking.robot.domain.tuling.data.EventInfo;
import com.thinking.robot.domain.tuling.data.TuLingRequestData;
import com.thinking.robot.domain.tuling.data.TuLingResponseData;
import com.thinking.robot.domain.tuling.data.TuLingRobotInfo;
import com.thinking.robot.domain.tuling.service.TuLingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class TuLingServiceImpl implements TuLingService {
    
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String TU_LING_URL_V2 = "http://openapi.tuling123.com/openapi/api/v2";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private TuLingRobotInfo tuLingRobotInfo;
    
    @Override
    public TuLingResponseData getRecallByText(final String text, final EventInfo eventInfo) {
        final TuLingRequestData.UserInfo userInfo = convertToUserInfo(eventInfo, tuLingRobotInfo.getApiKey());
        final TuLingRequestData tuLingRequestData = TuLingRequestData.getInstance(userInfo);
        tuLingRequestData.setReqType(0);
        final TuLingRequestData.Perception perception = new TuLingRequestData.Perception();
        final TuLingRequestData.InputText inputText = new TuLingRequestData.InputText();
        inputText.setText(text);
        perception.setInputText(inputText);
        tuLingRequestData.setPerception(perception);
        
        final String responseStr = restTemplate.postForObject(
                TU_LING_URL_V2,
                tuLingRequestData,
                String.class);
        try {
            final TuLingResponseData responseData = MAPPER.readValue(responseStr, TuLingResponseData.class);
            return responseData;
        } catch (JsonProcessingException e) {
            log.error("getRecallByText failed, request:{}, response:{}", tuLingRequestData, responseStr, e);
            return null;
        }
    }
    
    private static TuLingRequestData.UserInfo convertToUserInfo(EventInfo eventInfo, String apiKey){
        if(eventInfo == null || eventInfo.getUserId() == null){
            return null;
        }
        final TuLingRequestData.UserInfo userInfo = new TuLingRequestData.UserInfo();
        userInfo.setUserId(eventInfo.getUserId() != null ? String.valueOf(eventInfo.getUserId()) : null);
        userInfo.setGroupId(eventInfo.getGroupId() != null ? String.valueOf(eventInfo.getGroupId()) : null);
        userInfo.setUserIdName(eventInfo.getUserIdName());
        userInfo.setApiKey(apiKey);
        return userInfo;
    }
    
}
