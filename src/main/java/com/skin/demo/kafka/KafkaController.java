package com.skin.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/3 16:03
 */
@Slf4j
@RequestMapping("/kafka")
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping(value = "send")
    public void sendMessage() {
        int i = 0;
//        while (true){
        String str = ++i + "";
        ListenableFuture future = kafkaTemplate.send("test", str);
//            if(i==500){
        System.out.println("消息发送完成");
//                break;
//            }
//            future.addCallback(o -> log.info("send-消息发送成功：" + o.toString()), throwable -> log.info("消息发送失败：" + throwable.getMessage()));
//        }
    }
}
