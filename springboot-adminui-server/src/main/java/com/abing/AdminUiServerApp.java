package com.abing;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class AdminUiServerApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminUiServerApp.class, args);
    }
}
