package com.thinking.robot.domain.weather.assembler;

import com.thinking.robot.domain.weather.data.model.BaseInfo;
import com.thinking.robot.domain.weather.data.model.DailyWeather;
import com.thinking.robot.domain.weather.data.model.BaseDto;
import com.thinking.robot.domain.weather.data.model.DailyWeatherInfo;
import com.thinking.robot.domain.weather.data.model.LifeInfo;
import com.thinking.robot.domain.weather.data.model.Location;

public class DailyWeatherAssembler {
    
    private static final String NEXT_LINE = "\n";
    private static final String COLON = "：";
    private static final String SPACE = "    ";
    
    public static String dailyWeatherDtoAssemblerToText(final BaseDto dto){
        final StringBuilder stringBuilder = new StringBuilder();
    
        for (BaseInfo info : dto.getResults()) {
            String content = null;
            if(info instanceof DailyWeatherInfo){
                content = dailyWeatherInfoAssemblerToText((DailyWeatherInfo) info);
            } else if(info instanceof LifeInfo){
                content = lifeInfoAssemblerToText((LifeInfo) info);
            } else {
                continue;
            }
            stringBuilder.append(content).append(NEXT_LINE);
        }
        
        return stringBuilder.toString();
    }
    
    public static String lifeInfoAssemblerToText(final LifeInfo info){
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locationAssemblerToText(info.getLocation()))
                .append(NEXT_LINE);
        stringBuilder.append(suggestionAssemblerToText(info.getSuggestion()));
        return stringBuilder.toString();
    }
    
    public static String suggestionAssemblerToText(final LifeInfo.LifeSuggestion suggestion){
        final StringBuilder stringBuilder = new StringBuilder()
                .append(suggestionParameterAssemblerToText(suggestion.getCarWashing(), "洗车指数")).append(NEXT_LINE)
                .append(suggestionParameterAssemblerToText(suggestion.getDressing(), "穿衣指数")).append(NEXT_LINE)
                .append(suggestionParameterAssemblerToText(suggestion.getFlu(), "感冒指数")).append(NEXT_LINE)
                .append(suggestionParameterAssemblerToText(suggestion.getSport(), "运动指数")).append(NEXT_LINE)
                .append(suggestionParameterAssemblerToText(suggestion.getTravel(), "旅行指数")).append(NEXT_LINE)
                .append(suggestionParameterAssemblerToText(suggestion.getUv(), "紫外线指数"));
        return stringBuilder.toString();
    }
    
    public static String suggestionParameterAssemblerToText(final LifeInfo.Parameter parameter, final String name){
        final StringBuilder stringBuilder = new StringBuilder(name).append(COLON).append(parameter.getBrief())/*.append(NEXT_LINE)
                .append("详情").append(COLON).append(parameter.getDetails())*/;
        return stringBuilder.toString();
    }
    
    public static String dailyWeatherInfoAssemblerToText(final DailyWeatherInfo dailyWeatherInfo){
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locationAssemblerToText(dailyWeatherInfo.getLocation()))
                .append(NEXT_LINE);
    
        for (DailyWeather dailyWeather : dailyWeatherInfo.getDaily()) {
            stringBuilder.append(NEXT_LINE).append(NEXT_LINE).append(dailyWeatherAssemblerToText(dailyWeather));
        }
    
        return stringBuilder.toString();
    }
    
    public static String dailyWeatherAssemblerToText(final DailyWeather dailyWeather){
        final StringBuilder stringBuilder = new StringBuilder()
                .append("日期" + COLON).append(dailyWeather.getDate()).append(NEXT_LINE)
                .append("白天" + COLON).append(dailyWeather.getTextDay()).append(NEXT_LINE)
                .append("晚上" + COLON).append(dailyWeather.getTextNight()).append(NEXT_LINE)
                .append("湿度" + COLON).append(dailyWeather.getHumidity()).append(NEXT_LINE)
                .append("最高气温" + COLON).append(dailyWeather.getHigh()).append(NEXT_LINE)
                .append("最低气温" + COLON).append(dailyWeather.getLow());
        return stringBuilder.toString();
    }
    
    public static String locationAssemblerToText(final Location location){
        return location.getCountry() + SPACE + location.getName();
    }
}
