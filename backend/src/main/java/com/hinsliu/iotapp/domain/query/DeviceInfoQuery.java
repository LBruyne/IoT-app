package com.hinsliu.iotapp.domain.query;

/**
 * @Description: query for device detail info.
 * @author: liuxuanming
 * @date: 2021/04/17 8:55 下午
 */
public class DeviceInfoQuery {

    private String user;

    private String code;

    private String name;

    private String createTime;

    private String creatorName;

    private Integer type;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
}
