package com.hinsliu.iotapp.domain.view;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Description: dto for device info
 * @author: liuxuanming
 * @date: 2021/04/17 9:31 下午
 */
@Data
public class DeviceInfoDTO {

    private Integer id;

    private String code;

    private String name;

    private String description;

    private Date createTime;

    private String creatorName;

    private String type;

    private Date updateTime;

}
