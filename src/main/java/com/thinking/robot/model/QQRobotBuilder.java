package com.thinking.robot.model;

import com.thinking.robot.listener.FriendListener;
import com.thinking.robot.listener.GroupListener;
import com.thinking.robot.tuling.service.TuLingService;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.SystemDeviceInfoKt;

import java.io.File;

/**
 * @author nanke
 * @date 2020/6/28 上午11:48
 */
public class QQRobotBuilder {

    public static Bot bot = null;
    
    public static Bot initAndGetRobot(RobotInfo robotInfo, TuLingService tuLingService){
        if (bot == null || !bot.isOnline()) {
            if(bot != null && !bot.isOnline()){
                // 清理连接，防止内存泄漏
                bot.close(null);
            }
            
            bot = BotFactoryJvm.newBot(robotInfo.getQqId(), robotInfo.getPassword(), new BotConfiguration() {{
                // 设备缓存信息
                setDeviceInfo(context -> SystemDeviceInfoKt.loadAsDeviceInfo(new File("deviceInfo.json"), context));
            }});
            // 登陆
            bot.login();
            Events.registerEvents(bot, new FriendListener(robotInfo, tuLingService));
            Events.registerEvents(bot, new GroupListener(robotInfo, tuLingService));
        }
        return bot;
    }
}
