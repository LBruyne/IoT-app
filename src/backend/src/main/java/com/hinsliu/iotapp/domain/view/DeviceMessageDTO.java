package com.hinsliu.iotapp.domain.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description: dto for device message.
 * @author: liuxuanming
 * @date: 2021/04/17 9:36 下午
 */
@Data
public class DeviceMessageDTO {

    private Integer id;

    private String clientId;

    private String info;

    private Integer value;

    private Integer alert;

    private Double lng;

    private Double lat;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date timestamp;

}
