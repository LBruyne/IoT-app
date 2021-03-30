package com.hinsliu.iotapp.domain.model.common;

import java.io.Serializable;

/**
 * @Description: model for user.
 * @author: liuxuanming
 * @date: 2021/03/25 1:33 下午
 */
public class UserInfoDO implements Serializable {

    private Integer id;

    private String userName;

    private String userPassword;

    private String email;

    private String avatarUrl;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
