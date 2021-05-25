package com.hinsliu.iotapp.dal.message;

import com.hinsliu.iotapp.domain.model.DeviceMessageDO;
import com.hinsliu.iotapp.domain.query.DeviceMessageQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: Dao for MQTT message.
 * @author: liuxuanming
 * @date: 2021/04/15 8:27 下午
 */
public interface DeviceMessageDao {

    Integer insert(DeviceMessageDO mqttMessageDO);

    Integer queryTotalCount(DeviceMessageQuery query);

    List<DeviceMessageDO> queryByPage(DeviceMessageQuery query);
}
