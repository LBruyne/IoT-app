package com.hinsliu.iotapp.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hinsliu.iotapp.biz.utils.time.DateTimeUtil;
import com.hinsliu.iotapp.dal.device.DeviceDao;
import com.hinsliu.iotapp.dal.message.DeviceMessageDao;
import com.hinsliu.iotapp.domain.ErrorCodeEnum;
import com.hinsliu.iotapp.domain.UtilConstant;
import com.hinsliu.iotapp.domain.exception.BusinessException;
import com.hinsliu.iotapp.domain.model.DeviceDO;
import com.hinsliu.iotapp.domain.model.DeviceMessageDO;
import com.hinsliu.iotapp.domain.model.MqttMessageDO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: manager for mqtt subscribing
 * @author: liuxuanming
 * @date: 2021/04/15 11:06 上午
 */
@Component
@Service
@Slf4j
public class DeviceMessageManager implements MessageHandler {

    @Resource
    private DeviceMessageDao deviceMessageDao;

    @Resource
    private DeviceDao deviceDao;

    @SneakyThrows
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String payLoad = String.valueOf(message.getPayload());

        JSONObject jsonObject = JSON.parseObject(payLoad);
        // logger.info(jsonObject.toJavaObject(TestMessage.class).getName());

        // get received message.
        MqttMessageDO mqttMessageDO = jsonObject.toJavaObject(MqttMessageDO.class);

        // 检查该设备是否存在
        if (mqttMessageDO.getClientId() == null) {
            throw new BusinessException(ErrorCodeEnum.FAIL.getCode(), "设备ID不存在");
        }

        DeviceDO device = deviceDao.selectByClientId(mqttMessageDO.getClientId());
        log.info("根据收到MQTT消息的ID查询到设备 >> {}", device);
        if (device == null) {
            // 该设备不存在，创建新的设备
            DeviceDO item = createNewDevice(mqttMessageDO.getClientId());
            int affectCount = deviceDao.insert(item);
            if (affectCount > 0) {
                log.info("创建设备{} >> {}", item.getId(), affectCount);
            } else {
                log.warn("创建设备{}失败.", mqttMessageDO.getClientId());
                throw new BusinessException(ErrorCodeEnum.FAIL.getCode(), "创建设备失败");
            }
        } else {
            // 该设备已经存在，修改其更新时间
            device.setUpdateTime(DateTimeUtil.getCurrentDateTime());
            int affectCount = deviceDao.update(device);
            if (affectCount > 0) {
                log.info("更新设备{} >> {}", device.getId(), affectCount);
            } else {
                log.warn("更新设备{}失败.", device.getId());
                throw new BusinessException(ErrorCodeEnum.FAIL.getCode(), "更新设备失败");
            }
        }

        // 新建一条MQTT消息
        DeviceMessageDO deviceMessageDO = new DeviceMessageDO();
        BeanUtils.copyProperties(mqttMessageDO, deviceMessageDO);
        deviceMessageDO.setTimestamp(DateTimeUtil.getCurrentDateTime());

        int affectCount = deviceMessageDao.insert(deviceMessageDO);
        if (affectCount > 0) {
            log.info("创建消息{} >> {}", deviceMessageDO.getId(), affectCount);
        } else {
            log.warn("创建消息{}失败", deviceMessageDO);
            throw new BusinessException(ErrorCodeEnum.FAIL.getCode(), "创建消息失败");
        }
    }

    private DeviceDO createNewDevice(String clientId) {
        return DeviceDO.builder()
                .code(clientId)
                .name(UtilConstant.DEFAULT_DEVICE_NAME)
                .createTime(DateTimeUtil.getCurrentDateTime())
                .creatorName(UtilConstant.DEFAULT_DEVICE_CREATOR)
                .type(UtilConstant.DEFAULT_TYPE)
                .updateTime(DateTimeUtil.getCurrentDateTime())
                .build();
    }
}