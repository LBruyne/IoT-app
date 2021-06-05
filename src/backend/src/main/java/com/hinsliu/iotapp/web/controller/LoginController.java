package com.hinsliu.iotapp.web.controller;

import com.hinsliu.iotapp.biz.UserInfoManager;
import com.hinsliu.iotapp.domain.RpcResult;
import com.hinsliu.iotapp.domain.query.UserLoginQuery;
import com.hinsliu.iotapp.domain.query.UserRegisterQuery;
import com.hinsliu.iotapp.domain.view.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: controller for login and registry services
 * @author: liuxuanming
 * @date: 2021/03/25 1:39 下午
 */
@Slf4j
@RestController
@RequestMapping("/app")
public class LoginController {

    @Resource
    private UserInfoManager userLoginManager;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public RpcResult<UserInfoDTO> login(@RequestBody @Validated UserLoginQuery query) {
         UserInfoDTO result = userLoginManager.login(query);
         return RpcResult.successResult(result);
    }

    @RequestMapping(value = "/callback")
    public RpcResult callback() {
        return RpcResult.errorResult("Unauthorized...");
    }

    @RequestMapping(value =  "/register", method = {RequestMethod.POST})
    public RpcResult register(@RequestBody @Validated UserRegisterQuery query) {
         userLoginManager.register(query);
         return RpcResult.successResult();
    }

}
