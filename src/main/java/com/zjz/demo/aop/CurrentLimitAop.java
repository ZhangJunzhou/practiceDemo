package com.zjz.demo.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.zjz.demo.annotation.CurrentLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName CurrentLimitAop
 * @Author Junzhou Zhang
 * @Create 2022/6/10 16:35
 */
@Aspect
@Component
public class CurrentLimitAop {

    private RateLimiter rateLimiter = RateLimiter.create(1.0);
    private ConcurrentHashMap<String, RateLimiter> rateLimiterConcurrentHashMap = new ConcurrentHashMap<>();

    @Before(value = "@annotation(com.zjz.demo.annotation.CurrentLimit)")
    public void before(){
        System.out.println("------------前置通知-----------");
    }

    @AfterReturning(value = "@annotation(com.zjz.demo.annotation.CurrentLimit)")
    public void afterReturing(){
        System.out.println("------------后置通知-----------");
    }

    @Around(value = "@annotation(com.zjz.demo.annotation.CurrentLimit)")
    public Object around(ProceedingJoinPoint joinPoint){
        try {
            //获取当前方法的API
            //获取拦截的方法名
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            CurrentLimit declaredAnnotation = methodSignature.getMethod().getDeclaredAnnotation(CurrentLimit.class);

            // 获取该注解的方法名和token
            String name = declaredAnnotation.name();
            double token = declaredAnnotation.token();

            RateLimiter rateLimiter = rateLimiterConcurrentHashMap.get(name);

            // 判断是否包含该方法的 rateLimiter
            if(rateLimiter == null){
                rateLimiter =  RateLimiter.create(token);
                rateLimiterConcurrentHashMap.put(name, rateLimiter);
            }

            boolean result = rateLimiter.tryAcquire();
            if(!result){
                return "当前访问人数过多，请稍后重试！";
            }

            System.out.println("环绕通知开始执行------");
            joinPoint.proceed();
            System.out.println("环绕通知执行结束------");

        } catch (Throwable throwable) {
            return "系统错误!";
        }
        return "环绕通知";
    }
}
