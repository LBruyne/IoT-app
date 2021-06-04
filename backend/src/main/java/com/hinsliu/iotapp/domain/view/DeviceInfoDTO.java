package com.hinsliu.iotapp.domain.view;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String creatorName;

    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

}
