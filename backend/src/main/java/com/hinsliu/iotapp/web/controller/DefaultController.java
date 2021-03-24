package com.hinsliu.iotapp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Default controller for test.
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

    // Test PathVariable
    @RequestMapping(value = "/index/pathvariable/{params}")
    public Map<String, Object> pathVariableTest(@PathVariable("params") String param) {
        return new HashMap<String, Object>(){{
           put("PathVariable param", param);
        }};
    }

    // Test RequestParam
    @RequestMapping(value = "/index/requestparam")
    public Map<String, Object> requestParamTest(@RequestParam("params") String param) {
        return new HashMap<String, Object>(){{
            put("RequestParam param", param);
        }};
    }

    // Test RequestBody
    @RequestMapping(value = "/index/requestbody")
    public String requestBodyTest(@RequestBody String param) {
        return "body is: " + param;
    }

    // Test text
    // POST Request, HttpServletRequest
    @RequestMapping(value = "/index/text")
    public String textTest(HttpServletRequest request) {
        ServletInputStream is = null;
        try {
            is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            System.out.println(sb.toString());
            return "获取到的文本内容为：" + sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
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

