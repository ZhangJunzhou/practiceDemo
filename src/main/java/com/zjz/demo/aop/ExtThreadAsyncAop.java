package com.zjz.demo.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.zjz.demo.annotation.CurrentLimit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @ClassName ExtThreadAsyncAop
 * @Author Junzhou Zhang
 * @Create 2022/6/13 15:39
 */
@Aspect
@Component
@Slf4j
public class ExtThreadAsyncAop {

    @Around(value = "@annotation(com.zjz.demo.annotation.ZjzAsync)")
    public void around(ProceedingJoinPoint joinPoint) {
        log.info("环绕通知开始执行------");
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                joinPoint.proceed();
            }
        }).start();
        //            Object proceed = joinPoint.proceed();  //目标方法：
        log.info("环绕通知执行结束------");
    }
}
