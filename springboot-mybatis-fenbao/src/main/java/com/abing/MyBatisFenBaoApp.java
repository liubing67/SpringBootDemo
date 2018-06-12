package com.abing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  1.mybatis启动方式可以在mapper层不需要加mapper注解，但是一定要在启动类的时候加@MapperScan(basePackages = {"com.abing.mapper"})
 *  2.mybatis在mybatis接口加上@mapper注入myabtis容器，就不需要在启动类的时候加上@MapperScan(basePackages = {"com.abing.mapper"})
 */

/**
 * 内容为，使用分包方式拆分数据源、及事务处理（添加指定事务管理器 @Transactional(transactionManager = "test1TransactionManager")）
 */
@SpringBootApplication
public class MyBatisFenBaoApp {

    public static void main(String [] args){
        SpringApplication.run(MyBatisFenBaoApp.class,args);
    }
}
