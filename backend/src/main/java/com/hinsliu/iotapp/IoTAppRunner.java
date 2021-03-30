package com.hinsliu.iotapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.hinsliu.iotapp.dal")
public class IoTAppRunner {

    public static void main(String[] args) {
        SpringApplication.run(IoTAppRunner.class, args);
    }

}
