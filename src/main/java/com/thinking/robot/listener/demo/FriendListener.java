package com.thinking.robot.listener.demo;

import com.thinking.robot.model.QQRobotBuilder;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.FriendMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.SingleMessage;
import org.jetbrains.annotations.NotNull;

/**
 * @author nanke
 * @date 2020/6/28 下午2:22
 */
public class FriendListener extends SimpleListenerHost {

    private static final Long youFriendQQ = null;

    /**
     * Java方法级别注解,标注一个方法为事件监听器
     * @param event
     */
    @EventHandler
    public void onMessage(FriendMessageEvent event) {

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
                 * 这是向好友回复图片
                 */
                event.getSender().sendMessage(image);
            } catch (Exception e) {}
        }

        /**
         * 好友列表寻找某位好友,定向式发送信息
         */
        Friend friend = QQRobotBuilder.bot.getFriend(event.getSender().getId());
        friend.sendMessage("这是好友私聊定向消息");
        /**
         * 向事件触发方发送,触发式发送信息
         */
        event.getSender().sendMessage("这是好友私聊触发消息");
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        /**
         * 异常处理方式
         * 先直接打印堆栈吧～
         */
        exception.printStackTrace();
    }
}
