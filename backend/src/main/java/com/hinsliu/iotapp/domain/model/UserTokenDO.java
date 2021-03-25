package com.hinsliu.iotapp.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: model for user login token
 * @author: liuxuanming
 * @date: 2021/03/25 2:15 下午
 */
public class UserTokenDO implements Serializable {

    private Integer id;

    private String token;

    private String createTime;

    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
