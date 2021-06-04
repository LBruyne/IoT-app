package com.hinsliu.iotapp.domain.query;

import com.hinsliu.iotapp.domain.PageParam;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: query for message for a device.
 * @author: liuxuanming
 * @date: 2021/04/17 9:12 下午
 */
@Data
public class DeviceMessageQuery extends PageParam {

    private String code;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
