package com.thinking.robot.domain.facade.modulemanager.rules;

import com.thinking.robot.domain.facade.modulemanager.ModuleManager;
import com.thinking.robot.utils.compiler.CompilerExecutor;
import net.mamoe.mirai.message.MessageEvent;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.PlainText;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

public class CompilerRule extends BaseRule {
    private static final int LEVEL = 0;
    private static final String PREFIX = "编译运行";
    
    public CompilerRule(ModuleManager moduleManager) {
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
        return StringUtils.startsWithIgnoreCase(text.getContent().trim(), PREFIX);
    }
    
    @Override
    public List<Message> buildRecord(MessageEvent event) {
    
        PlainText text = event.getMessage().first(PlainText.Key);
        String source = StringUtils.replace(text.getContent().trim(), PREFIX, "");
        source = StringUtils.replace(source, "…", "...");
//        source = StringUTF8Convert.toUTF8(source);
        String result;
        try {
            result = CompilerExecutor.run(source);
        } catch (Exception e) {
            result = e.getMessage();
            e.printStackTrace();
        }
        return Collections.singletonList(new PlainText(result));
    }
}
