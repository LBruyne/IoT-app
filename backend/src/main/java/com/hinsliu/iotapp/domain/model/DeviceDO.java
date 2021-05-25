package com.hinsliu.iotapp.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: model for IoT devices.
 * @author: liuxuanming
 * @date: 2021/03/25 1:33 下午
 */
@Data
@Builder
public class DeviceDO {

    private Integer id;

    private String code;

    private String name;

    private String description;

    private Date createTime;

    private String creatorName;

    private Integer type;

    private Date updateTime;

}
