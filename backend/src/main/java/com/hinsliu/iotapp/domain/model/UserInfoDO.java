package com.hinsliu.iotapp.domain.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: model for user.
 * @author: liuxuanming
 * @date: 2021/03/25 1:33 下午
 */
@Data
public class UserInfoDO implements Serializable {

    private Integer id;

    private String userName;

    private String userPassword;

    private String email;

    private String avatarUrl;

}
