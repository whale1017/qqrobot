package com.thinking.robot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nanke
 * @date 2020/6/30 下午5:06
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventExplain {

    /**
     * 解释器类型
     * @return
     */
    String type();


}
