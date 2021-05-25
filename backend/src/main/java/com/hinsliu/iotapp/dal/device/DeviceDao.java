package com.hinsliu.iotapp.dal.device;

import com.hinsliu.iotapp.domain.model.DeviceDO;
import com.hinsliu.iotapp.domain.query.DeviceInfoQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: Dao for device info.
 * @author: liuxuanming
 * @date: 2021/04/15 8:27 下午
 */
public interface DeviceDao {

    DeviceDO selectByClientId(@Param("clientId") String clientId);

    List<DeviceDO> queryByPage(DeviceInfoQuery query);

    Integer queryTotalCount(DeviceInfoQuery query);

    Integer insert(DeviceDO item);

    Integer update(DeviceDO item);
    
}
