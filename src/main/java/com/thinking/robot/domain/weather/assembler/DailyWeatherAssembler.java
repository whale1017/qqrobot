package com.thinking.robot.domain.weather.assembler;

import com.thinking.robot.domain.weather.data.DailyWeather;
import com.thinking.robot.domain.weather.data.DailyWeatherDto;
import com.thinking.robot.domain.weather.data.DailyWeatherInfo;
import com.thinking.robot.domain.weather.data.Location;

public class DailyWeatherAssembler {
    
    private static final String NEXT_LINE = "\n";
    private static final String COLON = "：";
    private static final String SPACE = "    ";
    
    public static String dailyWeatherDtoAssemblerToText(final DailyWeatherDto dto){
        final StringBuilder stringBuilder = new StringBuilder();
    
        for (DailyWeatherInfo info : dto.getResults()) {
            stringBuilder.append(dailyWeatherInfoAssemblerToText(info)).append(NEXT_LINE);
        }
        
        return stringBuilder.toString();
    }
    
    public static String dailyWeatherInfoAssemblerToText(final DailyWeatherInfo dailyWeatherInfo){
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locationAssemblerToText(dailyWeatherInfo.getLocation()))
                .append(NEXT_LINE);
    
        for (DailyWeather dailyWeather : dailyWeatherInfo.getDaily()) {
            stringBuilder.append(dailyWeatherAssemblerToText(dailyWeather)).append(NEXT_LINE);
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
                .append("最低气温" + COLON).append(dailyWeather.getLow()).append(NEXT_LINE);
        return stringBuilder.toString();
    }
    
    public static String locationAssemblerToText(final Location location){
        return location.getCountry() + SPACE + location.getName() + NEXT_LINE;
    }
}
