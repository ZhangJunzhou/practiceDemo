package com.zjz.demo.service;

import com.google.common.util.concurrent.RateLimiter;
import com.zjz.demo.annotation.CurrentLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberService
 * @Author Junzhou Zhang
 * @Create 2022/6/10 16:01
 */
@RestController
public class MemberService {
    private String name;

//    private RateLimiter rateLimiter = RateLimiter.create(2.0);

    @GetMapping("/get")
    @CurrentLimit(name = "get", token = 1)
    public String get(){
//        boolean result = rateLimiter.tryAcquire();
//        if(!result){
//            return "当前访问人数过多，请稍后重试！";
//        }
        return  "my is get";
    }

    @GetMapping("/add")
    @CurrentLimit(name = "get", token = 10)
    public String add(){
        return "my is add";
    }

}
