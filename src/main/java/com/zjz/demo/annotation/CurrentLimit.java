package com.zjz.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName CurrentLimit
 * @Author Junzhou Zhang
 * @Create 2022/6/10 16:29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentLimit {
    /**
     * name 限流名称
     * token 每秒访问的次数 默认是20次
     * @return
     */
    String name() default "";
    double token() default 20;
}
