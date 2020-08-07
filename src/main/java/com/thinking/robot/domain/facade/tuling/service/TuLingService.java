package com.thinking.robot.domain.facade.tuling.service;

import com.thinking.robot.domain.facade.tuling.data.EventInfo;
import com.thinking.robot.domain.facade.tuling.data.TuLingResponseData;

public interface TuLingService {
    
    TuLingResponseData getRecallByText(final String text, final EventInfo eventInfo);
    
}
