package com.zjz.demo.service;

import com.zjz.demo.manage.OrderManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderService
 * @Author Junzhou Zhang
 * @Create 2022/6/13 15:42
 */
@RestController
@Slf4j
public class OrderService {

    @Autowired
    private OrderManage orderManage;

    @RequestMapping("/addOrder")
    public String addOrder(){
        log.info("<1>");
        orderManage.asyncLog();
        log.info("<3>");
        return "333";
    }
}
