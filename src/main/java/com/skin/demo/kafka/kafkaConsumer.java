package com.skin.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/3 16:56
 */
@Component
@Slf4j
public class kafkaConsumer {

    /**
     * 批量消费
     *
     * @param consumerRecord
     * @param ack
     * @throws Exception
     */

    @KafkaListener(topics = {"test"}, containerFactory = "batchFactory")
    public void listen(List<ConsumerRecord> consumerRecord, Acknowledgment ack) throws Exception {
        log.info("收到的消息" + consumerRecord.size());
        for (ConsumerRecord record : consumerRecord) {
            Optional msg = Optional.ofNullable(record.value());
            if (msg.isPresent()) {
                Object message = msg.get();
//                System.out.println("----record:"+record);
//                System.out.println("----message:"+message);
            }
        }
        Thread.sleep(5000);
        System.out.println("睡五秒在提交");
        System.out.println("处理完成。提交确认机制");
        ack.acknowledge();
    }

/*
    //批量消费
    @KafkaListener(topics = {"test"})
    public void listen(String message){
        log.info("收到的消息"+message);
    }*/

    @Bean
    public KafkaListenerContainerFactory<?> batchFactory(ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(1);
        factory.getContainerProperties().setPollTimeout(1000);
        //设置为批量消费，每个批次数量在Kafka配置参数中设置
        factory.setBatchListener(true);
        //设置手动提交ackMode
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }
}
