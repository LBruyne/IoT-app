package com.hinsliu.iotapp.dal.message;

import com.hinsliu.iotapp.domain.model.message.DeviceMessageDO;
import com.hinsliu.iotapp.domain.model.message.MqttMessageDO;
import org.springframework.stereotype.Repository;

/**
 * @Description: Dao for MQTT message.
 * @author: liuxuanming
 * @date: 2021/04/15 8:27 下午
 */
@Repository
public interface MqttMessageDao {

    /**
     * @description: insert received mqtt message.
     * @author: liuxuanming
     * @date: 2021/4/15 8:31 下午
     * @params: [deviceMessage]
     * @return: java.lang.Integer
     * @param mqttMessageDO
     */
    public Integer insert(DeviceMessageDO mqttMessageDO);
}
