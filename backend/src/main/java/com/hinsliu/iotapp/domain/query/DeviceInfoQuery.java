package com.hinsliu.iotapp.domain.query;

import com.hinsliu.iotapp.domain.PageParam;
import lombok.Data;

import java.util.Date;

/**
 * @Description: query for device detail info.
 * @author: liuxuanming
 * @date: 2021/04/17 8:55 下午
 */
@Data
public class DeviceInfoQuery extends PageParam {

    private String code;

    private String name;

    private String creatorName;

    private Integer type;

    private Date startTime;

    private Date endTime;

}
