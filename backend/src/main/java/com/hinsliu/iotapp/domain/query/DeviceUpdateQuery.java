package com.hinsliu.iotapp.domain.query;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description: query for updating device info.
 * @author: liuxuanming
 * @date: 2021/04/17 3:13 下午
 */
@Data
public class DeviceUpdateQuery {

    @NotNull(message = "设备ID不能为空")
    private Integer id;

    private String code;

    private String name;

    private String description;

    private String creatorName;

    private Date createTime;

    private Integer type;

    private Date updateTime;

}
