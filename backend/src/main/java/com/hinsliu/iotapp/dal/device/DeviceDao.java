package com.hinsliu.iotapp.dal.device;

import com.hinsliu.iotapp.domain.model.device.IoTDeviceDO;
import com.hinsliu.iotapp.domain.query.DeviceInfoQuery;
import com.hinsliu.iotapp.domain.query.DeviceUpdateQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: Dao for device info.
 * @author: liuxuanming
 * @date: 2021/04/15 8:27 下午
 */
@Repository
public interface DeviceDao {

    /**
     * @description: check device existed by client id.
     * @author: liuxuanming
     * @date: 2021/4/15 8:34 下午
     * @params: [clientId]
     * @return: com.hinsliu.iotapp.domain.model.message.DeviceMessageDO
     */
    public IoTDeviceDO selectByClientId(@Param("clientId") String clientId);
    
    /**
     * @description: query by conditions.
     * @author: liuxuanming
     * @date: 2021/4/18 1:33 下午
     * @params: [query]
     * @return: java.util.List<com.hinsliu.iotapp.domain.model.device.IoTDeviceDO>
     */
    public List<IoTDeviceDO> queryByPage(DeviceInfoQuery query);

    /**
     * @description: get total count of query by conditions
     * @author: liuxuanming
     * @date: 2021/4/18 3:01 下午
     * @params: [query]
     * @return: java.lang.Integer
     */
    Integer queryTotalCount(DeviceInfoQuery query);

    /**
     * @description: insert new devices.
     * @author: liuxuanming
     * @date: 2021/4/15 8:42 下午
     * @params: [ioTDeviceDO]
     * @return:
     */
    public Integer insert(IoTDeviceDO ioTDeviceDO);

    /**
     * @description: update device info.
     * @author: liuxuanming
     * @date: 2021/4/17 5:13 下午
     * @params: [query]
     * @return: java.lang.Integer
     */
    public Integer updateDeviceInfo(DeviceUpdateQuery query);
    
}
