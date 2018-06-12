package com.abing;

import com.abing.config.DBConfig1;
import com.abing.config.DBConfig2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 *  1.mybatis启动方式可以在mapper层不需要加mapper注解，但是一定要在启动类的时候加@MapperScan(basePackages = {"com.abing.mapper"})
 *  2.mybatis在mybatis接口加上@mapper注入myabtis容器，就不需要在启动类的时候加上@MapperScan(basePackages = {"com.abing.mapper"})
 */

/**
 * 内容为，多数据源分布式事务解决方案 使用jta+atomikos
 */

//开启读取配置文件
@EnableConfigurationProperties(value = {DBConfig1.class, DBConfig2.class})
@SpringBootApplication
public class MyBatisFenBaoFenBuShiApp {

    public static void main(String [] args){
        SpringApplication.run(MyBatisFenBaoFenBuShiApp.class,args);
    }
}
