package com.hinsliu.iotapp.domain.view;

import lombok.Data;

import java.util.Date;

/**
 * @Description: dto for device message.
 * @author: liuxuanming
 * @date: 2021/04/17 9:36 下午
 */
@Data
public class DeviceMessageDTO {

    private String clientId;

    private String info;

    private Integer value;

    private Integer alert;

    private Double lng;

    private Double lat;

    private Date timestamp;

}
