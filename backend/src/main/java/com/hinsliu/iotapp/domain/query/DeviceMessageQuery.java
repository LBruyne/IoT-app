package com.hinsliu.iotapp.domain.query;

/**
 * @Description: query for message for a device.
 * @author: liuxuanming
 * @date: 2021/04/17 9:12 下午
 */
public class DeviceMessageQuery {

    private String user;

    private String code;

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
}
