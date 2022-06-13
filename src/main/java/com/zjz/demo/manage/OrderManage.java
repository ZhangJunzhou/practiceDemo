package com.zjz.demo.manage;

import com.zjz.demo.annotation.ZjzAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderManage
 * @Author Junzhou Zhang
 * @Create 2022/6/13 15:40
 */
@Component
@Slf4j
public class OrderManage {

    @ZjzAsync
    public void asyncLog(){
        try{
            log.info("目标方法正在执行，正在阻塞3s------");
            Thread.sleep(3000);
        } catch (Exception exception){
            System.out.println("系统错误！");
        }
    }
}
