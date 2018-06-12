package com.abing;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.abing.dao"})
@EntityScan(basePackages = {"com.abing.entity"})
@SpringBootApplication
public class JpaApp {

    public static void main(String args[]){
        SpringApplication.run(JpaApp.class,args);
    }
}
