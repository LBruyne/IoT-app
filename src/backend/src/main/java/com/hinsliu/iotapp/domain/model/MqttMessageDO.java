package com.hinsliu.iotapp.domain.model;

import lombok.Data;

/**
 * @Description: model for device massage
 * @author: liuxuanming
 * @date: 2021/04/15 8:16 下午
 */
@Data
public class MqttMessageDO {

    private String clientId;

    private String info;

    private Integer value;

    private Integer alert;

    private Double lng;

    private Double lat;

    private Long timestamp;

}
