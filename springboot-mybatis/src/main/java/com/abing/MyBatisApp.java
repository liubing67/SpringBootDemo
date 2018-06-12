package com.abing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  1.mybatis启动方式可以在mapper层不需要加mapper注解，但是一定要在启动类的时候加@MapperScan(basePackages = {"com.abing.mapper"})
 *  2.mybatis在mybatis接口加上@mapper注入myabtis容器，就不需要在启动类的时候加上@MapperScan(basePackages = {"com.abing.mapper"})
 */

/**
 * 主要内容为，SpringBoot整合Mybatis、   @Transactional管理
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.abing.mapper"})
public class MyBatisApp {

    public static void main(String [] args){
        SpringApplication.run(MyBatisApp.class,args);
    }
}
