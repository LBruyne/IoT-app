package com.hinsliu.iotapp.domain.query;

/**
 * @Description: Query for user login.
 * @author: liuxuanming
 * @date: 2021/03/28 9:25 上午
 */
public class UserLoginQuery {

    private String userName;

    private String userPassword;

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

}
