package com.hinsliu.iotapp.domain.query;

import com.hinsliu.iotapp.domain.PageParam;

import java.io.Serializable;

/**
 * @Description: query for device detail info.
 * @author: liuxuanming
 * @date: 2021/04/17 8:55 下午
 */
public class DeviceInfoQuery extends PageParam implements Serializable {

    private String user;

    private String code;

    private String name;

    private String creatorName;

    private Integer type;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
