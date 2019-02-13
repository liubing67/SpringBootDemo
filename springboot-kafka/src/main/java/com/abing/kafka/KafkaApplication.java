package com.abing.kafka;

import com.abing.kafka.provider.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KafkaApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

        KafkaSender sender = context.getBean(KafkaSender.class);

        for (int i = 0; i < 30000; i++) {
            //调用消息发送类中的消息发送方法
            sender.send(i);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}