package com.hinsliu.iotapp.domain.query;

import com.hinsliu.iotapp.domain.PageParam;

/**
 * @Description: query for message for a device.
 * @author: liuxuanming
 * @date: 2021/04/17 9:12 下午
 */
public class DeviceMessageQuery extends PageParam {

    private String user;

    private String code;

    private String startTime;

    private String endTime;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
