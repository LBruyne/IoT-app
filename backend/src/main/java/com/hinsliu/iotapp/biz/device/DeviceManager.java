package com.hinsliu.iotapp.biz.device;

import com.hinsliu.iotapp.biz.common.UserLoginManager;
import com.hinsliu.iotapp.dal.device.DeviceDao;
import com.hinsliu.iotapp.dal.message.MqttMessageDao;
import com.hinsliu.iotapp.domain.RpcResult;
import com.hinsliu.iotapp.domain.query.DeviceInfoQuery;
import com.hinsliu.iotapp.domain.query.DeviceMessageQuery;
import com.hinsliu.iotapp.domain.query.DeviceUpdateQuery;
import com.hinsliu.iotapp.domain.view.DeviceInfoDTO;
import com.hinsliu.iotapp.domain.view.DeviceMesssageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @Description: manager for devices.
 * @author: liuxuanming
 * @date: 2021/04/15 11:07 上午
 */
public class DeviceManager {

    private static Logger logger = LoggerFactory.getLogger(DeviceManager.class);

    @Resource
    private DeviceDao deviceDao;

    @Resource
    private MqttMessageDao mqttMessageDao;

    /**
     * @description: 根据条件查询设备信息
     * @author: liuxuanming
     * @date: 2021/4/17 9:32 下午
     * @params: [query]
     * @return: com.hinsliu.iotapp.domain.RpcResult<com.hinsliu.iotapp.domain.view.common.DeviceInfoDTO>
     */
    public RpcResult<DeviceInfoDTO> queryByPage(DeviceInfoQuery query) {
        
    }

    /**
     * @description:
     * @author: liuxuanming
     * @date: 2021/4/17 9:46 下午
     * @params: [query]
     * @return: com.hinsliu.iotapp.domain.RpcResult<com.hinsliu.iotapp.domain.view.common.DeviceMessageDTO>
     */
    public RpcResult<DeviceMessageDTO> queryMessage(DeviceMessageQuery query) {

    }

    /**
     * @description:
     * @author: liuxuanming
     * @date: 2021/4/17 9:46 下午
     * @params: [query]
     * @return: com.hinsliu.iotapp.domain.RpcResult<java.lang.String>
     */
    public RpcResult<String> editDevice(DeviceUpdateQuery query) {

    }

    /**
     * @description:
     * @author: liuxuanming
     * @date: 2021/4/17 9:46 下午
     * @params: [query]
     * @return: com.hinsliu.iotapp.domain.RpcResult<java.lang.String>
     */
    public RpcResult<String> createDevice(DeviceUpdateQuery query) {

    }
}
