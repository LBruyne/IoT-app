package com.hinsliu.iotapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hinsliu.iotapp.dal")
public class IoTApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(IoTApplicationRunner.class, args);
    }

}
