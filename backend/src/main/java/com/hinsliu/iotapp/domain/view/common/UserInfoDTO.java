package com.hinsliu.iotapp.domain.view.common;

import com.hinsliu.iotapp.domain.model.common.UserInfoDO;

/**
 * @Description: DTO for user info
 * @author: liuxuanming
 * @date: 2021/03/30 11:20 上午
 */
public class UserInfoDTO {

    private Integer id;

    private String userName;

    private String email;

    private String avatarUrl;

    private String token;

    public Integer getId() {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
