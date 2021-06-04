package com.hinsliu.iotapp.web.controller;

import com.hinsliu.iotapp.biz.DeviceManager;
import com.hinsliu.iotapp.domain.Page;
import com.hinsliu.iotapp.domain.RpcResult;
import com.hinsliu.iotapp.domain.query.DeviceCreateQuery;
import com.hinsliu.iotapp.domain.query.DeviceInfoQuery;
import com.hinsliu.iotapp.domain.query.DeviceMessageQuery;
import com.hinsliu.iotapp.domain.query.DeviceUpdateQuery;
import com.hinsliu.iotapp.domain.view.DeviceInfoDTO;
import com.hinsliu.iotapp.domain.view.DeviceMessageDTO;
import com.hinsliu.iotapp.domain.annotation.AuthToken;
import com.hinsliu.iotapp.domain.view.StatisticDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: Controller of devices.
 * @author: liuxuanming
 * @date: 2021/03/31 9:36 下午
 */
@Slf4j
@RestController
@RequestMapping(value = "/app/device")
public class DeviceController {

    @Resource
    private DeviceManager deviceManager;

    @AuthToken
    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    public RpcResult<Page<DeviceInfoDTO>> getDeviceList(@RequestBody @Validated DeviceInfoQuery query) {
        return RpcResult.successResult(deviceManager.queryByPage(query));
    }

    @AuthToken
    @RequestMapping(value = "/message", method = {RequestMethod.POST})
    public RpcResult<Page<DeviceMessageDTO>> getDeviceMessage(@RequestBody @Validated DeviceMessageQuery query) {
        return RpcResult.successResult(deviceManager.queryMessage(query));
    }

    @AuthToken
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public RpcResult editDevice(@RequestBody @Validated DeviceUpdateQuery query) {
        deviceManager.editDevice(query);
        return RpcResult.successResult();
    }

    @AuthToken
    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public RpcResult createDevice(@RequestBody @Validated DeviceCreateQuery query) {
        deviceManager.createDevice(query);
        return RpcResult.successResult();
    }

    @AuthToken
    @RequestMapping(value = "/statistic", method = {RequestMethod.POST, RequestMethod.GET})
    public RpcResult<StatisticDTO> getStatistic() {
        return RpcResult.successResult(deviceManager.statistic());
    }
}
