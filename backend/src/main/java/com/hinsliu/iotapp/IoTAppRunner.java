package com.hinsliu.iotapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class IoTAppRunner {

    public static void main(String[] args) {
        SpringApplication.run(IoTAppRunner.class, args);
    }

}
