package com.hinsliu.iotapp.domain.query;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description: 创建设备信息请求
 * @author: liuxuanming
 * @date: 2021/05/25 6:50 下午
 */
@Data
public class DeviceCreateQuery {

    @NotNull(message = "设备编码不能为空")
    private String code;

    @NotNull(message = "设备名称不能为空")
    private String name;

    private String description;

    @NotNull(message = "创建人不能为空")
    private String creatorName;

    private Date createTime;

    @NotNull(message = "设备类型不能为空")
    private Integer type;

    private Date updateTime;

}
