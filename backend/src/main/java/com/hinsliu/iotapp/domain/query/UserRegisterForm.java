package com.hinsliu.iotapp.domain.query;

/**
 * @Description: Form object for register.
 * @author: liuxuanming
 * @date: 2021/03/30 4:23 下午
 */
public class UserRegisterForm {

    private String userName;

    private String userPassword;

    private String email;

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
}
