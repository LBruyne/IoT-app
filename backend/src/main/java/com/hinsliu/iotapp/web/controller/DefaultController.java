package com.hinsliu.iotapp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: Default controller.
 * @author: liuxuanming
 * @date: 2021/03/23 3:10 下午
 */
@RestController
@RequestMapping(value = "/default")
public class DefaultController {

    private static Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping(value = "/iot")
    public String hello() {
        return "Hello IoT App!";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "Hello Index Page!";
    }

    /*
    @RequestMapping(value = "/redis")
    public String redis(HttpServletRequest request) {
        String key = "Redis", value = "RedisValue";
        RedisUtil.set(key, value, 3);
        return RedisUtil.get(key);
    }
    */

}

