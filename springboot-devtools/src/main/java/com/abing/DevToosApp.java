package com.abing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 热部署整合
 * 在做热部署测试的时候，不要改当前方法测试
 *devtoos每次保存的时候，自动重启
 * 热部署类加载器，自带工具有热部署功能    springmvc装配 devtools是采用重启机制
 */
@SpringBootApplication
public class DevToosApp {
    public static void main(String args[]) {
        SpringApplication.run(DevToosApp.class, args);
    }

}
