package com.hinsliu.iotapp.biz.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hinsliu.iotapp.dal.device.DeviceDao;
import com.hinsliu.iotapp.dal.message.MqttMessageDao;
import com.hinsliu.iotapp.domain.model.device.IoTDeviceDO;
import com.hinsliu.iotapp.domain.model.message.DeviceMessageDO;
import com.hinsliu.iotapp.domain.model.message.MqttMessageDO;
import com.hinsliu.iotapp.domain.query.DeviceUpdateQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: manager for mqtt subscribing
 * @author: liuxuanming
 * @date: 2021/04/15 11:06 上午
 */
@Component
@Service
public class MqttMessageManager implements MessageHandler {

    private static final Integer DEFAULT_TYPE = 0;

    private static Logger logger = LoggerFactory.getLogger(MqttMessageManager.class);

    @Resource
    private MqttMessageDao mqttMessageDao;

    @Resource
    private DeviceDao deviceDao;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String payLoad = String.valueOf(message.getPayload());

        try {
            JSONObject jsonObject = JSON.parseObject(payLoad);
            // logger.info(jsonObject.toJavaObject(TestMessage.class).getName());

            // get received message.
            MqttMessageDO mqttMessageDO = jsonObject.toJavaObject(MqttMessageDO.class);

            // check if the device is existed.
            if(mqttMessageDO.getClientId() == null) return ;
            String clientId = mqttMessageDO.getClientId();
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(mqttMessageDO.getTimestamp()));

            IoTDeviceDO result = deviceDao.selectByClientId(clientId);

            if(result == null) {
                // device not existed.
                IoTDeviceDO newDevice = createNewDevice(clientId, timestamp);
                int ret = deviceDao.insert(newDevice);
                if(ret > 0) {
                    logger.info("Create new device {} succeed.", clientId);
                }
                else {
                    logger.warn("Create new device {} failed.", clientId);
                }
            }
            else {
                // build DeviceUpdateQuery object.
                DeviceUpdateQuery deviceUpdateQuery = new DeviceUpdateQuery();
                deviceUpdateQuery.setCode(clientId);
                deviceUpdateQuery.setUpdateTime(timestamp);
                // update device info.
                int ret = deviceDao.updateDeviceInfo(deviceUpdateQuery);
                if(ret <= 0) {
                    logger.warn("Update device failed.");
                }
            }

            // build message DO.
            DeviceMessageDO deviceMessageDO = new DeviceMessageDO();
            BeanUtils.copyProperties(mqttMessageDO, deviceMessageDO);
            deviceMessageDO.setTimestamp(timestamp);
            // insert info.
            int ret = mqttMessageDao.insert(deviceMessageDO);
            if(ret <= 0) logger.warn("Insert message failed.");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private IoTDeviceDO createNewDevice(String clientId, String timestamp) {
        IoTDeviceDO ioTDeviceDO = new IoTDeviceDO();
        ioTDeviceDO.setCode(clientId);
        ioTDeviceDO.setName("未命名");
        ioTDeviceDO.setCreateTime(timestamp);
        ioTDeviceDO.setCreatorName("未分配");
        ioTDeviceDO.setType(DEFAULT_TYPE);
        ioTDeviceDO.setUpdateTime(timestamp);
        return ioTDeviceDO;
    }
}