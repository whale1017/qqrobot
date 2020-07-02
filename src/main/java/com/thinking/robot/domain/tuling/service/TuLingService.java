package com.thinking.robot.domain.tuling.service;

import com.thinking.robot.domain.tuling.data.EventInfo;
import com.thinking.robot.domain.tuling.data.TuLingResponseData;

public interface TuLingService {
    
    TuLingResponseData getRecallByText(final String text, final EventInfo eventInfo);
    
}
