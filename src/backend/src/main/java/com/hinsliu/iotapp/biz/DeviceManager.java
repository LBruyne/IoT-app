package com.hinsliu.iotapp.biz;

import com.hinsliu.iotapp.biz.utils.time.DateTimeUtil;
import com.hinsliu.iotapp.dal.device.DeviceDao;
import com.hinsliu.iotapp.dal.message.DeviceMessageDao;
import com.hinsliu.iotapp.domain.*;
import com.hinsliu.iotapp.domain.exception.BusinessException;
import com.hinsliu.iotapp.domain.model.DeviceDO;
import com.hinsliu.iotapp.domain.model.DeviceMessageDO;
import com.hinsliu.iotapp.domain.query.DeviceCreateQuery;
import com.hinsliu.iotapp.domain.query.DeviceInfoQuery;
import com.hinsliu.iotapp.domain.query.DeviceMessageQuery;
import com.hinsliu.iotapp.domain.query.DeviceUpdateQuery;
import com.hinsliu.iotapp.domain.view.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: manager for devices.
 * @author: liuxuanming
 * @date: 2021/04/15 11:07 上午
 */
@Service
@Slf4j
public class DeviceManager {

    @Resource
    private DeviceDao deviceDao;

    @Resource
    private DeviceMessageDao deviceMessageDao;

    public Page<DeviceInfoDTO> queryByPage(DeviceInfoQuery query) {
        PageParam.verify(query);

        List<DeviceInfoDTO> dtoList = deviceDao.queryByPage(query)
                .stream()
                .map(doItem -> {
                    // 对每个doItem进行操作
                    DeviceInfoDTO dtoItem = new DeviceInfoDTO();

                    // 从DO到DTO进行拷贝
                    BeanUtils.copyProperties(doItem, dtoItem);

                    // type转换
                    dtoItem.setType(DeviceTypeEnum.getType(doItem.getType()).getName());

                    log.info("查询到设备{}信息 >> {}", doItem.getId(), dtoItem);
                    return dtoItem;
                }).collect(Collectors.toList());

        // 构造返回结果
        return new Page<>(
                query.getPage(),
                query.getPageSize(),
                deviceDao.queryTotalCount(query),
                dtoList);
    }

    public Page<DeviceMessageDTO> queryMessage(DeviceMessageQuery query) {
        PageParam.verify(query);

        // 获取列表
        List<DeviceMessageDTO> dtoList = deviceMessageDao.queryByPage(query)
                .stream()
                .map(doItem -> {
                    DeviceMessageDTO dtoItem = new DeviceMessageDTO();
                    BeanUtils.copyProperties(doItem, dtoItem);
                    return dtoItem;
                }).collect(Collectors.toList());

        // 构造返回结果
        return new Page<>(
                query.getPage(),
                query.getPageSize(),
                deviceMessageDao.queryTotalCount(query),
                dtoList);
    }

    public void editDevice(DeviceUpdateQuery query) {
        DeviceDO item = DeviceDO.builder().build();
        BeanUtils.copyProperties(query, item);
        item.setUpdateTime(DateTimeUtil.getCurrentDateTime());

        int affectCount = deviceDao.update(item);
        if (affectCount > 0) {
            log.info("更新设备{} >> {}", item.getId(), affectCount);
        } else {
            log.warn("更新设备{}失败.", item.getId());
            throw new BusinessException(ErrorCodeEnum.FAIL.getCode(), "更新设备失败");
        }
    }

    public void createDevice(DeviceCreateQuery query) {
        DeviceDO item = DeviceDO.builder().build();
        BeanUtils.copyProperties(query, item);
        item.setUpdateTime(DateTimeUtil.getCurrentDateTime());
        item.setCreateTime(DateTimeUtil.getCurrentDateTime());

        // 插入项目信息，并将得到的id设置为record.id
        int affectCount = deviceDao.insert(item);
        if (affectCount > 0) {
            log.info("创建设备{} >> {}", item.getId(), affectCount);
        } else {
            log.warn("创建设备{}失败.", item.getName());
            throw new BusinessException(ErrorCodeEnum.FAIL.getCode(), "创建设备失败");
        }
    }

    public StatisticDTO statistic() {
        StatisticDTO statisticDTO = new StatisticDTO();

        List<DeviceDO> deviceDOList = deviceDao.getAllDevice();
        List<DeviceMessageDO> deviceMessageDOList = deviceMessageDao.getAllMessage();

        // 分类统计数据
        List<Integer> typeData = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0));
        for(DeviceDO item : deviceDOList) {
            Integer type = item.getType();
            typeData.set(type, typeData.get(type) + 1);
        }
        statisticDTO.setTypeData(typeData);

        // PieData统计数据
        PieGraphDTO pieGraphDTO = new PieGraphDTO();
        List<String> typeNameList = new LinkedList<>();
        for(DeviceTypeEnum typeEnum : DeviceTypeEnum.values()) {
            typeNameList.add(typeEnum.getName());
        }
        pieGraphDTO.setNames(typeNameList);
        pieGraphDTO.setNumbers(typeData);
        statisticDTO.setPieData(pieGraphDTO);

        return statisticDTO;
    }
}
