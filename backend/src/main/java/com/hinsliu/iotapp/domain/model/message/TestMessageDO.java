package com.hinsliu.iotapp.domain.model.message;

/**
 * @Description: Model for tet message
 * @author: liuxuanming
 * @date: 2021/04/15 8:19 下午
 */
public class TestMessageDO {

    private String name;

    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
