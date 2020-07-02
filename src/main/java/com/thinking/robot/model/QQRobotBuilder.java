package com.thinking.robot.model;

import com.thinking.robot.application.task.data.RobotInfo;
import com.thinking.robot.application.task.listener.FriendListener;
import com.thinking.robot.application.task.listener.GroupListener;
import com.thinking.robot.domain.modulemanager.ModuleManager;
import com.thinking.robot.domain.tuling.service.TuLingService;
import com.thinking.robot.domain.tuling.service.impl.TuLingServiceImpl;
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
    
    public static Bot initAndGetRobot(RobotInfo robotInfo, ModuleManager moduleManager){
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
            Events.registerEvents(bot, new FriendListener(robotInfo, moduleManager));
            Events.registerEvents(bot, new GroupListener(robotInfo, moduleManager));
        }
        return bot;
    }
}
