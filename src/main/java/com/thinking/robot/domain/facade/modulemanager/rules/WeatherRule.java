package com.thinking.robot.domain.facade.modulemanager.rules;

import com.thinking.robot.domain.facade.modulemanager.ModuleManager;
import com.thinking.robot.domain.facade.weather.assembler.DailyWeatherAssembler;
import com.thinking.robot.domain.facade.weather.data.model.BaseDto;
import net.mamoe.mirai.message.MessageEvent;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.PlainText;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WeatherRule extends BaseRule {
    private static final int LEVEL = 1;
    
    public WeatherRule(ModuleManager moduleManager) {
        super(moduleManager, LEVEL);
    }
    
    @Override
    public boolean checkAccess(MessageEvent event) {
    
        if (event == null) {
            return false;
        }
        PlainText text = event.getMessage().first(PlainText.Key);
        if(text ==  null){
            return false;
        }
        String[] strings = text.getContent().trim().split(" ");
        return strings.length >= 2 && Objects.equals(strings[0], "天气");
    
    }
    
    @Override
    public List<Message> buildRecord(MessageEvent event) {
        PlainText text = event.getMessage().first(PlainText.Key);
        String[] strings = text.getContent().trim().split(" ");
        BaseDto dto = moduleManager.getWeatherService().searchDailyWeather(strings[1], "0", 3);
        return Collections.singletonList(new PlainText(DailyWeatherAssembler.dailyWeatherDtoAssemblerToText(dto)));
    }
}
