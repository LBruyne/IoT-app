package com.hinsliu.iotapp.dal.message;

import com.hinsliu.iotapp.domain.model.message.DeviceMessageDO;
import com.hinsliu.iotapp.domain.model.message.MqttMessageDO;
import com.hinsliu.iotapp.domain.query.DeviceMessageQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * @description: query message by condition.
     * @author: liuxuanming
     * @date: 2021/4/18 3:24 下午
     * @params: [query]
     * @return: java.lang.Integer
     */
    Integer queryTotalCount(DeviceMessageQuery query);

    /**
     * @description: query total message count.
     * @author: liuxuanming
     * @date: 2021/4/19 10:25 上午
     * @params: [query]
     * @return: java.util.List<com.hinsliu.iotapp.domain.model.message.DeviceMessageDO>
     */
    List<DeviceMessageDO> queryByPage(DeviceMessageQuery query);
}
