package com.hinsliu.iotapp.web.controller;

import com.hinsliu.iotapp.biz.common.UserLoginManager;
import com.hinsliu.iotapp.domain.RpcResult;
import com.hinsliu.iotapp.domain.query.UserLoginQuery;
import com.hinsliu.iotapp.domain.query.UserRegisterForm;
import com.hinsliu.iotapp.domain.view.common.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @Description: controller for login and registry services
 * @author: liuxuanming
 * @date: 2021/03/25 1:39 下午
 */
@RestController
@RequestMapping("/app")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserLoginManager userLoginManager;

    /**
     * @description: user login
     * @author: liuxuanming
     * @date: 2021/3/28 9:23 上午
     * @params: []
     * @return: void
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public RpcResult<UserInfoDTO> login(@RequestBody(required = false) UserLoginQuery query) {
        return userLoginManager.loginValidation(query);
    }

    /**
     * @description: callback page of unauthorization.
     * @author: liuxuanming
     * @date: 2021/3/31 9:37 下午
     * @params: []
     * @return: com.hinsliu.iotapp.domain.RpcResult<java.lang.String>
     */
    @RequestMapping(value = "/callback")
    public RpcResult<String> callback() {
        return userLoginManager.unauthorizedCallback();
    }

    /**
     * @description: user register with form.
     * @author: liuxuanming
     * @date: 2021/3/31 9:37 下午
     * @params: [query]
     * @return: com.hinsliu.iotapp.domain.RpcResult<com.hinsliu.iotapp.domain.view.common.UserInfoDTO>
     */
    @RequestMapping(value =  "/register", method = {RequestMethod.POST})
    public RpcResult<UserInfoDTO> register(@RequestBody(required = false) UserRegisterForm query) {
        return userLoginManager.register(query);
    }

}
