package com.hinsliu.iotapp.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description: do for device message.
 * @author: liuxuanming
 * @date: 2021/04/17 5:11 下午
 */
@Data
public class DeviceMessageDO {

    private Integer id;

    private String clientId;

    private String info;

    private Integer value;

    private Integer alert;

    private Double lng;

    private Double lat;

    private Date timestamp;

}
