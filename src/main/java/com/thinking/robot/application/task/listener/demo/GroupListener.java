package com.thinking.robot.application.task.listener.demo;

import com.thinking.robot.model.QQRobotBuilder;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.SingleMessage;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

/**
 * @author nanke
 * @date 2020/7/1 下午12:26
 */
public class GroupListener extends SimpleListenerHost {


    @EventHandler
    public void onMessage(GroupMessageEvent event) {

        /**
         * 消息链
         */
        MessageChain messageChain = event.getMessage();
        /**
         * 真实消息体
         */
        SingleMessage message = messageChain.get(1);
        /**
         * 转为Mirai官方格式的字符串
         */
        String content = message.contentToString();

        if (content.contains("[图片]")) {
            try {
                /**
                 * 图片消息强转成Image类型
                 */
                Image image = (Image) message;
                /**
                 * Contact.sendMessage,发送API,有对Message的子类重载
                 * Contact -> User/Group -> Friend/Member 继承链
                 * 这是向群内回复图片
                 */
                event.getGroup().sendMessage(image);
                /**
                 * 骚操作:获取图片源地址,重新上传,即可将该图片转发给任意Contact
                 */
                String imageUrl = event.getBot().queryImageUrl(image);
                /**
                 * 这里至转发给群内发送用户
                 */
                Image friendImage = event.getSender().uploadImage(new URL(imageUrl));
                event.getSender().sendMessage(friendImage);
            } catch (Exception e) {}
        }

        /**
         * 群列表寻找指定群,定向式发送信息
         */
        Group group = QQRobotBuilder.bot.getGroup(event.getGroup().getId());
        group.sendMessage("这是群聊定向消息");
        /**
         * 向事件触发群发送,触发式发送信息
         */
        event.getGroup().sendMessage("这是群内触发回复");
        /**
         * 向事件触发群触发者发送,触发式发送信息
         */
        event.getSender().sendMessage("这是群内触发临时会话");
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }
}
