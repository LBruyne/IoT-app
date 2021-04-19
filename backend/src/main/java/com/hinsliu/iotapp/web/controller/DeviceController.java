package com.hinsliu.iotapp.web.controller;

import com.hinsliu.iotapp.biz.common.UserLoginManager;
import com.hinsliu.iotapp.biz.device.DeviceManager;
import com.hinsliu.iotapp.domain.Page;
import com.hinsliu.iotapp.domain.RpcResult;
import com.hinsliu.iotapp.domain.query.DeviceInfoQuery;
import com.hinsliu.iotapp.domain.query.DeviceMessageQuery;
import com.hinsliu.iotapp.domain.query.DeviceUpdateQuery;
import com.hinsliu.iotapp.domain.view.common.DeviceInfoDTO;
import com.hinsliu.iotapp.domain.view.common.DeviceMessageDTO;
import com.hinsliu.iotapp.web.annotation.AuthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: Controller of devices.
 * @author: liuxuanming
 * @date: 2021/03/31 9:36 下午
 */
@RestController
@RequestMapping(value = "/app/device")
public class DeviceController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Resource
    private DeviceManager deviceManager;

    @AuthToken
    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    public RpcResult<Page<DeviceInfoDTO>> getDeviceList(@RequestBody(required = false) DeviceInfoQuery query) {
        return deviceManager.queryByPage(query);
    }

    @AuthToken
    @RequestMapping(value = "/message", method = {RequestMethod.POST})
    public RpcResult<Page<DeviceMessageDTO>> getDeviceMessage(@RequestBody DeviceMessageQuery query) {
        return deviceManager.queryMessage(query);
    }

    @AuthToken
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public RpcResult<String> editDevice(@RequestBody DeviceUpdateQuery query) {
        return deviceManager.editDevice(query);
    }

    @AuthToken
    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public RpcResult<String> createDevice(@RequestBody DeviceUpdateQuery query) {
        return deviceManager.createDevice(query);
    }
}
