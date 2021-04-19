package com.hinsliu.iotapp.biz.device;

import com.hinsliu.iotapp.biz.common.UserLoginManager;
import com.hinsliu.iotapp.dal.device.DeviceDao;
import com.hinsliu.iotapp.dal.message.MqttMessageDao;
import com.hinsliu.iotapp.domain.Page;
import com.hinsliu.iotapp.domain.RpcResult;
import com.hinsliu.iotapp.domain.model.device.IoTDeviceDO;
import com.hinsliu.iotapp.domain.model.message.DeviceMessageDO;
import com.hinsliu.iotapp.domain.query.DeviceInfoQuery;
import com.hinsliu.iotapp.domain.query.DeviceMessageQuery;
import com.hinsliu.iotapp.domain.query.DeviceUpdateQuery;
import com.hinsliu.iotapp.domain.view.common.DeviceInfoDTO;
import com.hinsliu.iotapp.domain.view.common.DeviceMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: manager for devices.
 * @author: liuxuanming
 * @date: 2021/04/15 11:07 上午
 */
@Service
public class DeviceManager {

    private static final int DEVICE_MAX_PAGE_SIZE = 10;

    private static final int MESSAGE_MAX_PAGE_SIZE = 100;

    private static final Integer DEFAULT_OPTION = 0;

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
    public RpcResult<Page<DeviceInfoDTO>> queryByPage(DeviceInfoQuery query) {
        RpcResult<Page<DeviceInfoDTO>> rpcResult = RpcResult.errorResult("Query failed.");

        // 检查user合法
        if(query.getUser() == null || query.getUser().equals("")) return rpcResult;

        try {
            // 检查page合法性
            if(query.getPage() == null || query.getPage() <= 0) {
                query.setPage(1);
            }
            // 检查pageSize合法性
            int maxPageSize = DEVICE_MAX_PAGE_SIZE;
            if(query.getPageSize() == null || query.getPageSize() >= maxPageSize) {
                query.setPageSize(maxPageSize);
            }
            // 检查offset合法性
            if(query.getOffset() == null) {
                query.setOffset();
            }

            // 获取列表
            List<IoTDeviceDO> doList = deviceDao.queryByPage(query);
            List<DeviceInfoDTO> dtoList = new ArrayList<>();
            if(doList != null && doList.size() > 0) {
                for(IoTDeviceDO doItem : doList) {
                    // 从DO到DTO进行拷贝
                    DeviceInfoDTO dtoItem = new DeviceInfoDTO();
                    BeanUtils.copyProperties(doItem, dtoItem);
                    // TODO TYPE转换
                    dtoList.add(dtoItem);
                }
            }

            // 构造返回结果
            Page<DeviceInfoDTO> result = new Page<>(
                    query.getPage(),
                    query.getPageSize(),
                    deviceDao.queryTotalCount(query),
                    dtoList);
            rpcResult = RpcResult.successResult(result);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return rpcResult;
    }

    /**
     * @description:
     * @author: liuxuanming
     * @date: 2021/4/17 9:46 下午
     * @params: [query]
     * @return: com.hinsliu.iotapp.domain.RpcResult<com.hinsliu.iotapp.domain.view.common.DeviceMessageDTO>
     */
    public RpcResult<Page<DeviceMessageDTO>> queryMessage(DeviceMessageQuery query) {
        RpcResult<Page<DeviceMessageDTO>> rpcResult = RpcResult.errorResult("Query failed.");

        // 检查user合法
        if(query.getUser() == null || query.getUser().equals("")) return rpcResult;

        try {
            // 检查page合法性
            if(query.getPage() == null || query.getPage() <= 0) {
                query.setPage(1);
            }
            // 检查pageSize合法性
            int maxPageSize = MESSAGE_MAX_PAGE_SIZE;
            if(query.getPageSize() == null || query.getPageSize() >= maxPageSize) {
                query.setPageSize(maxPageSize);
            }
            // 检查offset合法性
            if(query.getOffset() == null) {
                query.setOffset();
            }

            // 获取列表
            List<DeviceMessageDO> doList = mqttMessageDao.queryByPage(query);
            List<DeviceMessageDTO> dtoList = new ArrayList<>();
            if(doList != null && doList.size() > 0) {
                for(DeviceMessageDO doItem : doList) {
                    // 从DO到DTO进行拷贝
                    DeviceMessageDTO dtoItem = new DeviceMessageDTO();
                    BeanUtils.copyProperties(doItem, dtoItem);
                    dtoList.add(dtoItem);
                }
            }

            // 构造返回结果
            Page<DeviceMessageDTO> result = new Page<>(
                    query.getPage(),
                    query.getPageSize(),
                    mqttMessageDao.queryTotalCount(query),
                    dtoList);
            rpcResult = RpcResult.successResult(result);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return rpcResult;
    }

    /**
     * @description:
     * @author: liuxuanming
     * @date: 2021/4/17 9:46 下午
     * @params: [query]
     * @return: com.hinsliu.iotapp.domain.RpcResult<java.lang.String>
     */
    public RpcResult<String> editDevice(DeviceUpdateQuery query) {
        RpcResult<String> rpcResult = RpcResult.errorResult("Edit device failed.");

        // 传入信息检查
        if(query.getName() == null || query.getName().equals("")) return rpcResult;
        else if(query.getUpdateTime() == null || query.getUpdateTime().equals("")) {
            return rpcResult;
        }

        try {
            int ret = deviceDao.updateDeviceInfo(query);
            if(ret > 0) {
                rpcResult = RpcResult.successResult();
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return rpcResult;
    }

    /**
     * @description:
     * @author: liuxuanming
     * @date: 2021/4/17 9:46 下午
     * @params: [query]
     * @return: com.hinsliu.iotapp.domain.RpcResult<java.lang.String>
     */
    public RpcResult<String> createDevice(DeviceUpdateQuery query) {
        RpcResult<String> rpcResult = RpcResult.errorResult("Create device failed.");

        // 传入信息检查
        if(query.getName() == null) {
            logger.warn("CREATE: Invalid device name.");
            return rpcResult;
        }
        if(query.getCreatorName() == null || query.getCreatorName().equals("") ||
                query.getCreateTime() == null || query.getCreateTime().equals("")) {
            logger.warn("CREATE: Invalid creator name or timestamp.");
            return rpcResult;
        }
        if(query.getUpdateTime() == null || query.getUpdateTime().equals("")) {
            query.setUpdateTime(query.getCreateTime());
        }
        if(query.getType() == null) {
            query.setType(DEFAULT_OPTION);
        }

        // 建立record对象，并对必要信息补充
        IoTDeviceDO record = new IoTDeviceDO();
        BeanUtils.copyProperties(query, record);

        try {
            // 插入项目信息，并将得到的id设置为record.id
            int ret = deviceDao.insert(record);
            if(ret > 0) {
                rpcResult = RpcResult.successResult();
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return rpcResult;
    }
}
